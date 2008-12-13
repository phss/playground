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
class ClosestPair

  def self.find_shortest_cycle(points)
    connector = EdgeConnector.new(points)
    
    while connector.unclosed?
      
    end
  end
  
end

class EdgeConnector
  
  def initialize(points)
    @vertex_chains = points
  end
  
  def unclosed?
    @vertex_chains.size > 1
  end  
  
end

class VertexChain
  
  def initialize(initial_point)
    @points = [initial_point]
  end
  
  def start_point
    @points.first
  end
  
  def end_point
    @points.last
  end
  
  def connect(another_chain)
    if 
  end
  
end