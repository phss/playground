# O(n log n)
#
# Create a heap from the array and extract the min until it's over.
class Heapsort

  def sort(array)
    heap = Heap.from(array)
    sorted = []

    sorted << heap.extract_min while heap.has_element?

    return sorted
  end

end

class Heap
  def initialize
    @array = []
  end

  def self.from(array)
    heap = Heap.new
    array.each { |e| heap.insert(e) }
    return heap
  end

  def insert(element)
    @array << element
    bubble_up(@array.size-1)
  end

  def extract_min
    nil unless has_element?

    swap(0, @array.size-1)
    min = @array.pop
    bubble_down(0)

    return min
  end

  def has_element?
    !@array.empty?
  end

  private 

  def bubble_up(index)    
    parent_index = ((index+1)/2)-1
    
    if @array[index] < @array[parent_index]
      swap(index, parent_index)
      bubble_up(parent_index) unless parent_index == 0
    end
  end

  def bubble_down(index)
    left_child_index, right_child_index = (index+1)*2-1, (index+1)*2
    left_child, right_child = @array[left_child_index], @array[right_child_index]

    return if (left_child.nil? || @array[index] < left_child) && (right_child.nil? || @array[index] < right_child)
    
    swap_index = right_child.nil? || left_child < right_child ? left_child_index : right_child_index
    swap(index, swap_index)
    bubble_down(swap_index)
  end

  def swap(i, j)
    @array[i], @array[j] = @array[j], @array[i]      
  end

end
