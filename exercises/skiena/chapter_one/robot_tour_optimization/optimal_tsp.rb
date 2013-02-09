require "data_structures"

# 
# Algorithm:
#   d = infinity
#   For each of the n! permutations P_i of point set P
#     If (cost(P_i) <= d) then d = cost(P_i) and P_min = P_i
#   Return P_min
#   
# Note:
#   This solution is, of course, unacceptable. :)
#  
class OptimalTSP

  def self.find_shortest_cycle(points)
    shortest_cycle = Cycle.from_list_of_points(points.clone)
    for_each_permutation_of(points) do |permutation|
      cycle = Cycle.from_list_of_points(permutation)
      shortest_cycle = cycle if (cycle.length <= shortest_cycle.length)
    end
    return shortest_cycle
  end
  
  def self.for_each_permutation_of(array)
    if array.size == 1
      yield array
    else
      array.each_with_index do |elem, i|
        for_each_permutation_of(array[0, i] + array[(i+1)..-1]) { |permutation| yield permutation.insert(0, elem) }
      end
    end
  end
  
end