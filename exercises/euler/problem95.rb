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

sum = sum_of_divisors_up_to(1000000)

puts sum[28]
