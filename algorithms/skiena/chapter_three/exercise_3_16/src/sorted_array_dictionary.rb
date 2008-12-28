#
# This implementation is *not* using the usual Ruby Array stuff for didatic purposes. That is why
# I am doing a lot of sillyness with indexed and such.
#
class SortedArrayDictionary
  attr_reader :items
  
  def initialize
    @items = []
  end
  
  # O(log n)
  def search(key)
    index = index_of(key)
    return index.nil? ? nil : @items[index]
  end
  
  # O(n)
  def insert(an_item)
    @items.each_with_index do |item, index|
      if an_item < item
        @items.insert(index, an_item)
        return
      end
    end
    @items.insert(@items.size, an_item)
  end
  
  # O(n)
  def delete(an_item)
    index = index_of(an_item.key)
    if index
      @items[index] = nil
      @items.compact!
    end
  end
  
  # O(1)
  def max
    @items.last
  end
  
  # O(1)
  def min
    @items.first
  end
  
  # O(log n)
  def predecessor(an_item)
    return nil if an_item == min
    @items[index_of(an_item.key) - 1]
  end
  
  # O(log n)
  def successor(an_item)
    return nil if an_item == max
    @items[index_of(an_item.key) + 1]
  end
  
  def size
    @items.size
  end
  
  protected
  
  # O(log n)
  def index_of(key, lower=0, upper=@items.size-1)
    return nil if (upper - lower) < 0
    
    middle_item_index = lower + (upper - lower)/2
    return middle_item_index if key == @items[middle_item_index].key

    if key < @items[middle_item_index].key 
      return index_of(key, lower, middle_item_index-1) 
    else 
      return index_of(key, middle_item_index+1, upper)
    end
  end
  
end

Item = Struct.new(:key, :value) do
  include Comparable
  
  def <=>(other_item)
    self.key <=> other_item.key
  end
  
  def to_s
    self.key
  end
end