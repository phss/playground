require 'prime'

t = gets.chomp.to_i
t.times do |i|
  m, n = gets.chomp.split(" ").collect { |n| n.to_i }
  
  Prime.each do |prime|
    puts prime
    break if prime > n
  end
  
  puts "" unless i == t-1
end
