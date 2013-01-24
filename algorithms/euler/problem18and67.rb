
def triangle_from(filename)
  
end

def maximum_path_sum(triangle)
  next_level = triangle.last
  
  (triangle.size-2).downto(0) do |level|
    next_level = triangle[level].each_with_index.map do |e, i|
      e + [next_level[i], next_level[i+1]].max
    end
  end

  next_level.first
end

small_tri = [[3], [7, 4], [2, 4, 6], [8, 5, 9, 3]]

tri = []
File.open("files/problem67.txt") do |file|
  tri = file.readlines.map do |line|
    line.split(" ").map { |n| n.to_i }
  end
end

puts maximum_path_sum(tri)
