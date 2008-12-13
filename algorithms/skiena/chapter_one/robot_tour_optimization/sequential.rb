require "data_structures"

# Very dumb implementation. Just create a sequential list of edges for the cycle
class Sequential
  
  def self.find_shortest_cycle(points)
    Cycle.from_list_of_points(points)
  end
  
end