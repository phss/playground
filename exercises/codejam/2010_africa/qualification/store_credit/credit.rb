
def items_to_buy(credit, items)
  items.each_with_index do |item1, i|
    items.each_with_index do |item2, j|
      next if i == j
      
      return [i+1, j+1] if credit == item1 + item2
    end
  end
end


n = gets.to_i

n.times do |i|
  credit = gets.to_i  
  gets
  items = gets.split.map { |n| n.to_i }

  puts "Case \##{i+1}: #{items_to_buy(credit, items).join(' ')}"
end
