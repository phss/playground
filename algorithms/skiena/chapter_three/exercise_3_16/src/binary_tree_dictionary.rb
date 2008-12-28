class BinaryTreeDictionary
  
  def initialize
    @root = nil
  end
  
  # O(h), where h is the height of the tree
  def search(key, node=@root)
    return nil if node.nil?
    return node.item if key == node.item.key
    return key < node.item.key ? search(key, node.left_child) : search(key, node.right_child)
  end
  
  # O(h), where h is the height of the tree
  def insert(an_item)
    insertion_node = find_node_for(an_item)
    insertion_node.nil? ? @root = Node.new(an_item) : insertion_node.add_child(an_item)
  end
  
  # O(h), where h is the height of the tree
  def delete(an_item)
    deletion_node = find_node(an_item)
    return if deletion_node.nil?
    
    if !deletion_node.has_child?
      deletion_node == @root ? @root = nil : deletion_node.parent.remove_child(deletion_node)
    else
      succ = deletion_node.right_child ? successor(an_item) : predecessor(an_item) 
      succ = find_node(succ)
      deletion_node.item = succ.item
      succ.parent.remove_child(succ)
    end
  end
  
  # O(h), where h is the height of the tree
  def max(node=@root)
    return nil if node.nil?
    largest = node
    largest = largest.right_child while largest.right_child
    return largest.item
  end
  
  # O(h), where h is the height of the tree
  def min(node=@root)
    return nil if node.nil?
    smallest = node
    smallest = smallest.left_child while smallest.left_child
    return smallest.item
  end
  
  # O(h), where h is the height of the tree
  def predecessor(an_item)
    previous = max(find_node(an_item).left_child)
    if previous.nil?
      previous = find_node(an_item).parent
      previous = previous.parent while previous && previous.item > an_item
      previous = previous.item if previous
    end
    return previous
  end
  
  # O(h), where h is the height of the tree
  def successor(an_item)
    next_item = min(find_node(an_item).right_child)
    if next_item.nil?
      next_item = find_node(an_item).parent
      next_item = next_item.parent while next_item && next_item.item < an_item
      next_item = next_item.item if next_item
    end
    return next_item
  end
  
  def size(node=@root)
    return 0 if node.nil?
    return size(node.left_child) + size(node.right_child) + 1
  end
  
  private
  
  # O(h), where h is the height of the tree
  def find_node_for(item, node=@root)
    return nil if node.nil?
    if item < node.item
      return node.left_child.nil? ? node : find_node_for(item, node.left_child)
    else
      return node.right_child.nil? ? node : find_node_for(item, node.right_child)
    end
  end
  
  # O(h), where h is the height of the tree
  def find_node(item, node=@root)
    return nil if node.nil?
    return node if node.item == item
    if item < node.item
      return find_node(item, node.left_child)
    else
      return find_node(item, node.right_child)
    end
  end
  
end

class Node
  include Comparable
  
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
  
  def remove_child(child_node)
    if child_node.item < @item
      @left_child = nil
    else
      @right_child = nil
    end
  end

  def has_child?
    @left_child || @right_child
  end

  def <=>(other_node)
    @item <=> other_node.item
  end
  
end

Item = Struct.new(:key, :value) do
  include Comparable
  
  def <=>(other_item)
    self.key <=> other_item.key
  end
end