#
# This implementation is *not* using the usual Ruby Array stuff for didatic purposes. That is why
# I am doing a lot of sillyness with indexed and such.
#
class UnsortedArrayDictionary
  
  def initialize
    @items = []
  end
  
  # O(n)
  def search(key)
    index = index_of(key)
    return index.nil? ? nil : @items[index]
  end
  
  # O(1)
  def insert(an_item)
    @items << an_item
  end
  
  # O(n)
  def delete(an_item)
    index = index_of(an_item.key)
    if index
      @items[index] = nil
      @items.compact!
    end
  end
  
  # O(n)
  def max
    @items.max
  end
  
  # O(n)
  def min
    @items.min
  end
  
  # O(n)
  def predecessor(an_item)
    previous_item = min
    @items.each do |item|
      previous_item = item if item < an_item && item > previous_item
    end
    return previous_item == an_item ? nil : previous_item    
  end
  
  # O(n)
  def successor(an_item)
    next_item = max
    @items.each do |item|
      next_item = item if item > an_item && item < next_item
    end
    return next_item == an_item ? nil : next_item
  end
  
  
  protected
  
  def index_of(key)
    @items.each_with_index { |item, index| return index if item.key == key }
    nil # If key not found
  end
  
end

Item = Struct.new(:key, :value) do
  include Comparable
  
  def <=>(other_item)
    self.key <=> other_item.key
  end
end