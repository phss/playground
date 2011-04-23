# O(n**2)
#
# For each element of an unsorted array, insert it into a sorted array.
# It can be faster then n**2 with the right data structure, but I am using a crappy
# array for it.
class InsertionSort

  def sort(array)
    sorted = []
    
    array.each do |element|
      sorted << element
      
      (sorted.size-1).downto(1) do |i|
        break if sorted[i] > sorted[i-1]
        sorted[i], sorted[i-1] = sorted[i-1], sorted[i]
      end
    end

    return sorted
  end

end
