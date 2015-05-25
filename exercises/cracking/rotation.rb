def rotations(i, s, e)
  [[i, s], [e, i], [e-i, e], [s, e-i]]
end

#puts rotations(3, 0, 4).map { |c| "(#{c.join(', ')})"}.join(' ')
