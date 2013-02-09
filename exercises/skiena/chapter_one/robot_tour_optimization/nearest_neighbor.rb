require "data_structures"

#
# Algorithm:
#   Pick and visit an initial point p_0 from P
#   p = p_0
#   i = 0
#   While there are still unvisited points
#     i = i + 1
#     Select p_i to be the closest unvisited point to p_i-1
#     Visit p_i
#   Return to p_0 from p_n-1
#
# Note:
#   This is a shortsighted implementation of the algorithm. I am only searching for the point closest 
#   to the end of the cycle (the last visited point). An improved approach would be to search for the 
#   closest to either the beginning or the end.
#
class NearestNeighbor
  
  def self.find_shortest_cycle(points)
    visitor = PointsVisitor.new(points)

    current_point = visitor.visit(points.first)
    while visitor.have_unvisited_points?
      current_point = visitor.visit_closest_to(current_point)
    end
    
    Cycle.from_list_of_points(visitor.visited_points)
  end
  
end

class PointsVisitor
  attr_reader :visited_points
  
  def initialize(points)
    @unvisited_points = points
    @visited_points = []
  end
  
  def visit(point)
    @visited_points << @unvisited_points.delete(point)
    point
  end
  
  def visit_closest_to(visited_point)
    closest_point = @unvisited_points.first
    @unvisited_points.each do |point|
      closest_point = point if (point.distance_to(visited_point) < closest_point.distance_to(visited_point))
    end
    visit(closest_point)
  end
  
  def have_unvisited_points?
    !@unvisited_points.empty?
  end
  
end