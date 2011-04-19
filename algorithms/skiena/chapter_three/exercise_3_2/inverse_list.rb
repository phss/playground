# Inverst a linked list

Node = Struct.new(:item, :next) 

def printl(node)
  if node.nil?
    puts("end_of_list")
    return
  end

  print "#{node.item}, "
  printl(node.next)
end

def invert(list)
  prev_node = list
  curr_node = list.next
  prev_node.next = nil
  while curr_node
    next_node = curr_node.next
    curr_node.next = prev_node
    prev_node = curr_node
    curr_node = next_node
  end
  prev_node
end

def recursive_invert(node, prev=nil)
  return prev if node.nil?

  nextn = node.next
  node.next = prev

  return recursive_invert(nextn, node)
end

list = nil
5.downto(1) { |i| list = Node.new(i, list) }

puts "- Original list"
printl(list)

puts "- Inverted list"
inverted = invert(list)
printl(inverted)

puts "- Inverted list (recursive)"
printl(recursive_invert(inverted))
