# Distribute 1000 data items into 1000 nodes (which can store 3 items) in such a way
# that no 2 items are in the same node

SIZE = 1000

# Very crappy method to check that the nodes don't contain duplicates or
# have more than one item in common with other nodes
def check_nodes(nodes)
  nodes.each do |node1|
    if (node1.size - node1.uniq.size) > 0
      puts "Node with duplicate: #{node1}" 
      return false 
    end
    nodes.each do |node2|
      same_elements = node2.inject(0) { |count, item| node1.include?(item) ? count + 1 : count }   
      if same_elements > 1 and node1 != node2
        puts "Nodes with more than one element in common: #{node1} and #{node2}"
        return false
      end
    end
  end
  return true
end

items = Array.new(SIZE) { |i| i }
nodes = Array.new(SIZE) { |i| [] }

#items.each { |item| nodes[item] = [item, item, item] } # All duplicates

#items.each { |item| nodes[item] << item; nodes[(item+1)%SIZE] << item; nodes[(item+2)%SIZE] << item } # Not so smart spread

items.each do |item|
  nodes[item] << item
  nodes[(item+1)%SIZE] << item
  nodes[(item+3)%SIZE] << item
end



puts check_nodes(nodes)
