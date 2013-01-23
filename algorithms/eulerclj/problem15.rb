
def lattice_paths(n)
  cols = n + 1
  size = (cols**2)
  grid = [1]

  1.upto(size-1) do |i|
    paths_left = ((i % cols) == 0) ? 0 : grid[i-i]
    paths_up = i < cols ? 0 : grid[i - cols]

    puts "At #{i} = #{paths_left} (#{i-1}) + #{paths_up} (#{i - cols})"

    grid << paths_left + paths_up

    puts grid.join(" ")
  end

  return grid.last
end


puts lattice_paths(3)
