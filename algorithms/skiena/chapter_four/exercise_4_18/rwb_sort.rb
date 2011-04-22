# Sort n elements of :red, :white:, blue where :red < :white < :blue in linear time

def examine(array, index)
  array[index]
end

def swap(array, i, j)
  temp = array[i]
  array[i] = array[j]
  array[j] = temp
end

def array_of_size(n)
  silly_map = {
    0 => :red,
    1 => :white,
    2 => :blue
  }
  Array.new(n) { silly_map[rand(3)] } 
end

def sort(array)
  partition_index = 0
  color = nil 
  partitioner = lambda do |element, i| 
    if examine(array, i) == color
      swap(array, partition_index, i)
      partition_index += 1  
    end
  end

  color = :red
  array.each_with_index(&partitioner)

  color = :white
  array.each_with_index(&partitioner)

  return array
end

puts sort(array_of_size(10)).join(" ")
