def divisors_up_to(n)
  divisors = Array.new(n+1) { [] }

  1.upto(n+1) do |num|
    multi = 2
    next_divisible = multi * num
    while next_divisible < (n+1)
      divisors[next_divisible] << num

      multi += 1
      next_divisible = multi * num
    end
  end

  return divisors
end

divisors = divisors_up_to(1000000)

#divisors.each_with_index do |divs, i|
  #puts "#{i}: #{divs.join(', ')}"
#end
