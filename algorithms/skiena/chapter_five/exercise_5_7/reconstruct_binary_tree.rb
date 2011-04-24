# Reconstruct binary trees from traversals

tree1_preorder  = [4, 2, 1, 3, 6, 5, 7]
tree1_postorder = [1, 3, 2, 5, 7, 6, 4]
tree1_inorder   = [1, 2, 3, 4, 5, 6, 7]

tree2_preorder  = [:a, :b, :d, :e, :f, :c]
tree2_postorder = [:d, :f, :e, :b, :c, :a]
tree2_inorder   = [:d, :b, :f, :e, :a, :c]

Node = Struct.new(:item, :left, :right)

def print_tree(tree)
  return if tree.nil?
  
  puts "#{tree.item} l-> #{tree.left.item}" unless tree.left.nil?
  puts "#{tree.item} r-> #{tree.right.item}" unless tree.right.nil?

  print_tree tree.left
  print_tree tree.right
end

def pre_in_construct(preorder, inorder)
  return nil if inorder.empty?

  elem = preorder.shift 
  index = inorder.index elem 

  left_side = index == 0 ? [] : inorder[0..index-1]
  right_side = inorder[index+1..inorder.size-1]

  return Node.new(elem, pre_in_construct(preorder, left_side), pre_in_construct(preorder, right_side))
end

puts "Tree1 (PreIn):"
print_tree pre_in_construct(tree1_preorder, tree1_inorder)
puts
puts "Tree2 (PreIn):"
print_tree pre_in_construct(tree2_preorder, tree2_inorder)
puts
