
def triangle_from(filename)
 tri = []
  File.open(filename) do |file|
    tri = file.readlines.map do |line|
      line.split(" ").map { |n| n.to_i }
    end
  end
end

def maximum_path_sum(triangle)
  triangle.reverse.reduce do |previous, current|
    current.each_with_index.map do |number, idx|
      number + [previous[idx], previous[idx + 1]].max
    end
  end
end

small_tri = [[3], [7, 4], [2, 4, 6], [8, 5, 9, 3]]

puts maximum_path_sum(triangle_from("files/problem18.txt"))
puts maximum_path_sum(triangle_from("files/problem67.txt"))
