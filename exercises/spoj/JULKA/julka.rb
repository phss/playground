10.times do
  total = gets.chomp.to_i
  diff  = gets.chomp.to_i
  
  n = (total - diff)/2
  k = total - n
  
  puts k
  puts n
end