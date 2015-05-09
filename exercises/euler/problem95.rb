def sum_of_divisors_up_to(n)
  divisors = Array.new(n+1) { 0 }

  1.upto(n+1) do |num|
    multi = 2
    next_divisible = multi * num
    while next_divisible < (n+1)
      divisors[next_divisible] += num

      multi += 1
      next_divisible = multi * num
    end
  end

  return divisors
end

sum = sum_of_divisors_up_to(1000000)

puts sum[220]
