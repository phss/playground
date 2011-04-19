# Convert a binary search tree into a linked list
require "binary_tree_dictionary"

tree = BinaryTreeDictionary.new
original = [2, 1, 4, 5, 0, 9, 8, 3]
original.each { |item| tree.insert(item) }

ListNode = Struct.new(:item, :next)

def printl(list)
  return if list.nil?
  print "#{list.item}, "
  printl(list.next)
end

def convert(tree_node, list=nil)
  return list if tree_node.nil?

  list_from_right = convert(tree_node.right_child, list)
  this_node = ListNode.new(tree_node.item, list_from_right)
  return convert(tree_node.left_child, this_node)
end


puts "Original array: #{original}"
puts "Converted tree into list:"
printl(convert(tree.root))
