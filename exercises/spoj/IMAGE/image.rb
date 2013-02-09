while (line = gets.chomp) && line != "0 0 0 0"  
  rows, columns, cr, cc = line.split(" ")
  rows = rows.to_i
  columns = columns.to_i
  
  grid = Array.new(rows.to_i) do
    gets.chomp.split("")
  end
  
  perimeter = 0
  visit = [[cr.to_i-1, cc.to_i-1]]
  while !visit.empty?
    row, column = visit.shift
    next if grid[row][column] == "V"
    grid[row][column] = "V"
    [[row-1, column], [row+1, column], [row, column-1], [row, column+1]].each do |nb|
      nb_row, nb_column = nb
      if nb_row < 0 || nb_row >= rows || 
         nb_column < 0 || nb_column >= columns || 
         grid[nb_row][nb_column] == "."
        perimeter += 1
      elsif grid[nb_row][nb_column] == "X"
        visit.push(nb)
      end
    end
    [[row-1, column-1], [row-1, column+1], [row+1, column-1], [row+1, column+1]].each do |nb|
      nb_row, nb_column = nb
      if nb_row >= 0 && nb_row < rows &&
         nb_column >= 0 && nb_column < columns &&
         grid[nb_row][nb_column] == "X"
        visit.push(nb)
      end
    end
  end
  puts perimeter
end