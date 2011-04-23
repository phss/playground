# O(n**2)
#
# For each element, find the smallest unsorted element and swap it with the it.
class SelectionSort

  # Inplace
  def sort(array)
    0.upto(array.size-2) do |i|
      smallest_index = i 

      (i+1).upto(array.size-1) do |j|
        smallest_index = j if array[smallest_index] > array[j]
      end

      array[i], array[smallest_index] = array[smallest_index], array[i]
    end

    return array
  end

end
