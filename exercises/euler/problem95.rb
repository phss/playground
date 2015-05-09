def sum_of_divisors_up_to(n)
  sums = Array.new(n+1) { 0 }

  1.upto(n/2) do |num|
    2.upto(n/num) do |multi|
      next_divisible = multi * num
      sums[next_divisible] += num
    end
  end

  return sums
end

def initialize_chain_array(sums)
  a = Array.new(sums.size) { -1 } 
  a[0] = -1
  a[1] = -1

  sums.each_with_index do |sum, i|
    a[i] = -1 if sum > 1000000
  end

  return a
end

sums = sum_of_divisors_up_to(1000000)
chain_array = initialize_chain_array(sums)

puts chain_array.size

