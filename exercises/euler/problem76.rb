def ways_to_make(amount)
  values = (1..(amount-1)).to_a
  ways = Array.new(amount + 1, 0)
  ways[0] = 1

  values.each do |value|
    (value..amount).each do |idx|
      ways[idx] += ways[idx - value]
    end    
  end

  return ways[amount]
end

puts ways_to_make(100)
