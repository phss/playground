# Given an array with positive and negative numbers (e.g. [1, 0, -2, 3, 2, 2, -10])
# calculate the largest sum of consecutive numbers. On the previous example, it would be 7


def largest_consecutive_sum(numbers)
  largest_sum = numbers.max
  current_sum = 0
  numbers.each do |number|
    current_sum += number
    if current_sum < 0
      current_sum = 0
      next
    end
    if current_sum > largest_sum
      largest_sum = current_sum
    end
  end
  return largest_sum
end

def calc_and_output(numbers, expected) 
  actual = largest_consecutive_sum(numbers)
  "#{numbers.join(' ')}: #{actual} == #{expected}"
end

puts calc_and_output([1, 0, -2, 3, 2, 2, -10], 7)
puts calc_and_output([-1, -2, 3, -2, -2, 10], 10) # edge case: last number is largest
puts calc_and_output([10, -20, 3, -2, 2, -10], 10) # edge case: first number is largest
puts calc_and_output([7, -2, 3, -2, 2, -10], 8) # sum goes up and down
puts calc_and_output([-32, -2, -30, -1, -5, -6], -1) # only negatives
