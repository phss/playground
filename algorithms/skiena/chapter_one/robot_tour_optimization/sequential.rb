require "data_structures"

# Very dumb implementation. Just create a sequential list of edges for the cycle
class Sequential
  
  def self.find_shortest_cycle(points)
    cycle = points.inject(Cycle.from_initial_edge(points.shift, points.shift)) do |result, point|
      result.add_point(point)
      result
    end
    cycle.add_point(cycle.edges.first.start_point) # Closing the cycle
    return cycle
  end
  
end