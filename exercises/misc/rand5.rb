# Implement rand5 based on rand7

def rand7
  rand(7) + 1
end

def rand5_repeating
  value = 7
  while value > 5
    value = rand7
  end
  value
end

def experiment(&fun)
  distribution = Array.new(5, 0)
  1000000.times do
    value = fun.call
    distribution[value-1] += 1   
  end
  distribution.join(' ')
end

puts 'Rand5 with repeating'
puts experiment(&method(:rand5_repeating))
