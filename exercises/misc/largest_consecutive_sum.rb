# Given an array with positive and negative numbers (e.g. [1, 0, -1, 3, 2, 2, -10])
# calculate the largest sum of consecutive numbers. On the previous example, it would be 7


def largest_consecutive_sum(numbers)
  5
end

def calc_and_output(numbers, expected) 
  actual = largest_consecutive_sum(numbers)
  "#{numbers.join(' ')}: #{actual} == #{expected}"
end

puts calc_and_output([1, 0, -1, 3, 2, 2, -10], 7)
