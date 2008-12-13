Point = Struct.new(:id, :x, :y) do
  def self.list_for(*coordinates_list)
    id = 0
    coordinates_list.map { |coordinate| Point.new(id += 1, coordinate[0], coordinate[1]) }
  end

  def to_s
    "#{self.id}"
  end
end

class Edge  
  attr_reader :start_point, :end_point
  
  def initialize(start_point, end_point)
    @start_point = start_point
    @end_point = end_point
  end
  
  def length
    Math.sqrt((@start_point.x - @end_point.x)**2 + (@start_point.y - @end_point.y)**2)
  end
  
  def to_s
    "(#{@start_point}, #{@end_point})"
  end
end

class Cycle
  attr_reader :edges
  
  def initialize(edges = [])
    @edges = edges
  end
  
  def self.from_initial_edge(start_point, end_point)
    Cycle.new([Edge.new(start_point, end_point)])
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
    output_string << "Length: #{length}" #-> Closed?: #{closed?}"
  end
end