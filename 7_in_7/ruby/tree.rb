class Tree
  attr_accessor :children, :node_name
  
  def initialize(name, children=[])
    @children = children
    @node_name = name
  end

  def self.from_hash(name, children)
    root = Tree.new(name)
    children.each do |child_name, child_children|
      root.children << Tree.from_hash(child_name, child_children)
    end
    return root
  end
  
  def visit_all(&block)
    visit &block
    children.each {|c| c.visit_all &block}
  end
  
  def visit(&block)
    block.call self
  end
end

ruby_tree = Tree.new( "Ruby", 
  [Tree.new("Reia"), 
   Tree.new("MacRuby")] )

puts "Visiting a node"
ruby_tree.visit {|node| puts node.node_name}
puts

puts "visiting entire tree"
ruby_tree.visit_all {|node| puts node.node_name}

puts "tree from hash"
hash_tree = Tree.from_hash('grandpa', { 'dad' => {'child 1' => {}, 'child 2' => {} }, 'uncle' => {'child 3' => {}, 'child 4' => {} } }) 
hash_tree.visit_all {|node| puts node.node_name}
