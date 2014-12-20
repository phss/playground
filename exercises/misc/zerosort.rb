# Given an array of numbers [1,2,3,8,0,2,2,0,10], move 0 to right and numbers to left keeping order


def zerosort(array)
  zeros_so_far = 0
  0.upto(array.size - 1) do |i|
    if array[i] == 0
      zeros_so_far += 1
    else
      j = i - zeros_so_far
      array[i], array[j] = array[j], array[i]
    end
  end
  array
end


puts zerosort([1,2,3,8,0,2,2,0,10]).join('-')
puts zerosort([0,0,0,0,0,0,0,1]).join('-')

