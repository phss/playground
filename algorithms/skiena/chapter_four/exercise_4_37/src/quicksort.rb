# O(n log n) average
# O(n**2) worst
#
# Partition the array and move smaller elements to the left and larger to right. Recurse
# into each side.
class Quicksort

  # Inplace
  def sort(array)
    quicksort(array, 0, array.size-1)
    return array
  end

  private

  def quicksort(array, left_index, right_index)
    return if left_index >= right_index

    split_index = partition(array, left_index, right_index)
    quicksort(array, left_index, split_index-1)
    quicksort(array, split_index+1, right_index)
  end

  def partition(array, left_index, right_index)
    split_index = left_index

    left_index.upto(right_index-1) do |i|
      if array[i] < array[right_index]
        swap(array, i, split_index)
        split_index += 1
      end
    end
    
    swap(array, split_index, right_index)
    return split_index
  end

  def swap(array, i, j)
    array[i], array[j] = array[j], array[i]
  end

end
