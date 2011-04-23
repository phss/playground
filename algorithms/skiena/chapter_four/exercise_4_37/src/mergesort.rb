# O(n log n)
#
# Recursively split array in two sides and merge the sorted results
class Mergesort

  # Inplace
  def sort(array)
    mergesort(array, 0, array.size-1)
    return array
  end

  private

  def mergesort(array, left_index, right_index)
    return if left_index >= right_index

    middle_index = left_index + (right_index - left_index)/2

    mergesort(array, left_index, middle_index)
    mergesort(array, middle_index+1, right_index)
    merge(array, left_index, middle_index, right_index)
  end

  def merge(array, left_index, middle_index, right_index)
    # Not quite a queue, I think
    left_queue = array[left_index..middle_index]
    right_queue = array[(middle_index+1)..right_index]
    
    index = left_index

    while !left_queue.empty? && !right_queue.empty?
      array[index] = left_queue.first < right_queue.first ? left_queue.shift : right_queue.shift
      index += 1
    end
    
    left_queue.each { |elem| array[index] = elem; index += 1 }
    right_queue.each { |elem| array[index] = elem; index += 1 }
  end

end
