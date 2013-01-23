
def lattice_paths(n)
  cols = n + 1
  size = (cols**2)
  grid = [1]

  1.upto(size-1) do |i|
    paths_left = ((i % cols) == 0) ? 0 : grid[i-1]
    paths_up = i < cols ? 0 : grid[i - cols]

    grid << paths_left + paths_up
  end

  return grid.last
end


puts lattice_paths(20)
