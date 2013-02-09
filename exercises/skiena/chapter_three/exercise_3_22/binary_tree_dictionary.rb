# Most of the functions are O(h), where h is the height of the tree. Ideally h = log n, in a balanced tree.
class BinaryTreeDictionary
  attr_reader :root
  
  def initialize
    @root = nil
  end
  
  # O(h)
  def search(key)
    key_node = find_node_for(key)
    return key_node.nil? ? nil : key_node.item
  end
  
  # O(h)
  def insert(an_item, node=@root)
    @root = Node.new(an_item) and return if @root.nil?
    
    node_path = an_item < node.item ? node.left_child : node.right_child 
    node_path.nil? ? node.add_child(an_item) : insert(an_item, node_path)
  end
  
  # O(h)
  def delete(an_item)
    node = find_node_for(an_item.key)
    return if node.nil?
    
    if node.has_child?
      swap_item = node.right_child ? successor(an_item) : predecessor(an_item) 
      delete(swap_item)
      node.item = swap_item
    else
      node == @root ? @root = nil : node.parent.unlink(node)
    end
  end
  
  # O(h)
  def max(node=@root)
    return go_down(node) { |n| n.right_child }
  end
  
  # O(h)
  def min(node=@root)
    return go_down(node) { |n| n.left_child }
  end
  
  # O(h) not great code
  def predecessor(an_item)
    find_next_item(an_item, max(find_node_for(an_item.key).left_child)) { |item, node| node.item > item }
  end
  
  # O(h) not great code
  def successor(an_item)
    find_next_item(an_item, min(find_node_for(an_item.key).right_child)) { |item, node| node.item < item }
  end
  
  def size(node=@root)
    return 0 if node.nil?
    return size(node.left_child) + size(node.right_child) + 1
  end
  
  private
  
  # O(h)
  def find_node_for(key, node=@root)
    return nil if node.nil?
    return node if node.item.key == key
    return key < node.item.key ? find_node_for(key, node.left_child) : find_node_for(key, node.right_child)
  end
  
  def go_down(node=@root)
    return nil if node.nil?
    current = node
    current = yield(current) while yield(current)
    return current.item
  end

  # Weird code
  def find_next_item(an_item, node)
    if node.nil?
      node = find_node_for(an_item.key).parent
      node = node.parent while node && yield(an_item, node)
      node = node.item if node
    end
    return node
  end
end

class Node
  attr_accessor :item, :parent, :left_child, :right_child
  
  def initialize(item, parent=nil)
    @item = item
    @parent = parent
    @left_child = nil
    @right_child = nil
  end
  
  def add_child(child_item)
    child_node = Node.new(child_item, self)
    child_item < @item ?  @left_child = child_node : @right_child = child_node
  end
  
  def unlink(child_node)
    if child_node.item < @item
      @left_child = nil
    else
      @right_child = nil
    end
    child_node.parent = nil
  end

  def has_child?
    @left_child || @right_child
  end
end

Item = Struct.new(:key, :value) do
  include Comparable
  
  def <=>(other_item)
    self.key <=> other_item.key
  end
end
