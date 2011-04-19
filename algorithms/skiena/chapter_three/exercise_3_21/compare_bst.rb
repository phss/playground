# Compare if two binary search trees are identical
require "binary_tree_dictionary"

tree1 = BinaryTreeDictionary.new
tree2 = BinaryTreeDictionary.new
tree3 = BinaryTreeDictionary.new
[2, 1, 4, 5, 0, 9, 8, 3].each { |item| tree1.insert(item); tree2.insert(item) }
[2, 4, 5, 0, 9, 8, 3].each { |item| tree3.insert(item) }

def compare_trees(node1, node2)
  return true if node1.nil? && node2.nil?
  return false if (node1.nil? != node2.nil?) || (node1.item != node2.item)
  return compare_trees(node1.left_child, node2.left_child) && compare_trees(node1.right_child, node2.right_child)
end


puts compare_trees(tree1.root, tree2.root)
puts compare_trees(tree1.root, tree3.root)
