n = gets.to_i

n.times do |i|
  words = gets
  puts "Case #{i+1}: #{words.split.reverse.join(' ')}" 
end
