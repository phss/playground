require "data_structures"

#
# Algorithm:
#   Let n be the number of points in set P
#   For i = 1 to n-1 do
#     d = infinity
#     For each pair of endpoints (s, t) from distinct vertex chains
#       if dist(s, t) <= d then s_m = s, t_m = t, and d = dist(s, t)
#     Connect (s_m, t_m) by an edge
#   Connect the two endpoints by an edge
#
# Note: not the greatest implementation ever. TODO refactor this!
#
class ClosestPair

  def self.find_shortest_cycle(points)
    connector = EdgeConnector.new(points)
    connector.connect_shortest_pair while connector.open?    
    Cycle.from_list_of_points(connector.end_chain.points)
  end
  
end

class EdgeConnector
  
  def initialize(points)
    @vertex_chains = points.map { |point| VertexChain.new(point) }
  end
  
  def open?
    @vertex_chains.size > 1
  end  
  
  def connect_shortest_pair
    return unless open?
    
    shortest_distance = 1000000 # Well, not quite infinite
    s = nil
    t = nil
   @vertex_chains.each do |chain_i|
      @vertex_chains.each do |chain_j|
        next if chain_i == chain_j
        if shortest_distance > chain_i.distance_to(chain_j)
          s = chain_i
          t = chain_j
          shortest_distance = chain_i.distance_to(chain_j)
        end
      end
    end
    s.connect(t)
    @vertex_chains.delete(t)
  end
  
  def end_chain
    @vertex_chains.first unless open?
  end
end

class VertexChain
  attr_reader :points
  
  def initialize(initial_point)
    @points = [initial_point]
  end
  
  def start_point
    @points.first
  end
  
  def end_point
    @points.last
  end

  def distance_to(another_chain)
    shortest_edge_to(another_chain).distance
  end
  
  def connect(another_chain)
    shortest_edge = shortest_edge_to(another_chain)
    insert_index = shortest_edge.connection_point == :start ? 0 : -1
    points_to_connect = another_chain.points
    points_to_connect.reverse! if shortest_edge.connection_point == shortest_edge.other_connection_point
    @points.insert(insert_index, points_to_connect).flatten!
  end
  
  private
  
  def shortest_edge_to(another_chain)
    [
     ConnectionEdge.new(:start, :start, start_point.distance_to(another_chain.start_point)), 
     ConnectionEdge.new(:start, :end, start_point.distance_to(another_chain.end_point)),
     ConnectionEdge.new(:end, :start, end_point.distance_to(another_chain.start_point)),
     ConnectionEdge.new(:end, :end, end_point.distance_to(another_chain.end_point))
    ].min
  end
      
end

# Bad naming
ConnectionEdge = Struct.new(:connection_point, :other_connection_point, :distance) do
  include Comparable
  
  def <=>(other)
    self.distance <=> other.distance
  end
end