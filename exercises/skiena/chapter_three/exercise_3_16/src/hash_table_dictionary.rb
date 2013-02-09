class HashTableDictionary
  attr_reader :table
  
  def initialize(table_size)
    @table = Array.new(table_size) { |i| [] }
  end
  
  # O(n/m)
  def search(key)
    @table[index_of(key)].each { |item| return item if item.key == key }
    nil # Item not found
  end
  
  # O(1)
  def insert(an_item)
    @table[index_of(an_item.key)] << an_item
  end
  
  # O(n/m)
  def delete(an_item)
    @table[index_of(an_item.key)].delete(an_item)
  end
  
  # O(n + m)
  def max
    @table.flatten.max
  end
  
  # O(n + m)
  def min
     @table.flatten.min
  end
  
  # O(n + m)
  def predecessor(an_item)
    return nil if an_item == min
    @table.flatten.inject(min) { |result, item| (item < an_item && item > result) ? item : result }
  end
  
  # O(n + m)
  def successor(an_item)
    return nil if an_item == max
    @table.flatten.inject(max) { |result, item| (item > an_item && item < result) ? item : result }    
  end
  
  def size
    @table.flatten.size
  end
  
  private
  
  # Not a great hashing code, but it works
  def index_of(item)
    hash_value = 0
    item.each_byte { |byte| hash_value += byte }
    hash_value % @table.size
  end
  
end

Item = Struct.new(:key, :value) do
  include Comparable

  def <=>(other_item)
    self.key <=> other_item.key
  end
end