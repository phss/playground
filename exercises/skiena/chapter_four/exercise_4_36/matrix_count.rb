# Counts number of zeros in a sorted matrix

class MatrixCount

  # O(2n)
  #
  # Due to the sorted order, a 0 can only be to the right of a zero
  # from a previous row. Thus, it goes to all rows (n) and goes at most
  # to all columns once (n).
  def MatrixCount.zeros_of(matrix)
    count = 0
    index = 0

    matrix.each do |row|
      row[index..row.size].each do |e| 
        if e >= 0
          count += 1 if e == 0
          break
        end
        index += 1
      end

      break if index == row.size
    end

    return count
  end

end
