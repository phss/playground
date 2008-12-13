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
  def initialize(pointA, pointB)
    @pointA = pointA
    @pointB = pointB
  end
  
  def to_s
    "(#{@pointA}, #{@pointB})"
  end
end

class Cycle
  def initialize(edges)
    @edges = edges
  end
  
  def to_s
    output_string = ""
    @edges.each { |edge| output_string << "#{edge} -> " }
    output_string << "#{@edges[0]}\n"
  end
end