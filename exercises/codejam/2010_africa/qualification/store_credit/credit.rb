
def items_to_buy(credit, items)
  [1, 2]
end


n = gets.to_i

n.times do |i|
  credit = gets.to_i  
  gets
  items = gets.split.map { |n| n.to_i }

  puts "Case \##{i+1}: #{items_to_buy(credit, items).join(' ')}"
end
