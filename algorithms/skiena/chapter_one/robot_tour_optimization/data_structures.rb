class Cycle
  attr_reader :edges
  
  def initialize(edges = [])
    @edges = edges
  end
  
  def self.from_initial_edge(start_point, end_point)
    Cycle.new([Edge.new(start_point, end_point)])
  end
  
  def self.from_list_of_points(points)
    cycle = points.inject(Cycle.from_initial_edge(points.shift, points.shift)) do |result, point|
      result.add_point(point)
      result
    end
    cycle.add_point(cycle.edges.first.start_point) # Closing the cycle
    return cycle
  end
  
  def add_point(point)
    @edges << Edge.new(@edges.last.end_point, point)
  end
  
  def closed?
    @edges.last.end_point == @edges.first.start_point
  end
  
  def length
    @edges.inject(0) { |result, edge| result += edge.length }
  end
  
  def to_s
    output_string = @edges.inject("") { |result, edge| result << "#{edge} -> " }
    output_string << "Length: #{length}"
  end
end


Edge = Struct.new(:start_point, :end_point) do
  def length
    start_point.distance_to(end_point)
  end
  
  def to_s
    "(#{start_point}, #{end_point})"
  end
end


Point = Struct.new(:id, :x, :y) do
  def self.list_for(*coordinates_list)
    id = 0
    coordinates_list.map { |coordinate| Point.new(id += 1, coordinate[0], coordinate[1]) }
  end
  
  def distance_to(point)
    Math.sqrt((self.x - point.x)**2 + (self.y - point.y)**2)
  end

  def to_s
    "#{self.id}"
  end
end