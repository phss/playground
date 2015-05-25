def print_matrix(m)
  m.each do |row|
    puts row.join(' ')
  end
end

def rotations(i, s, e)
  [[i, s], [e, i], [e-i, e], [s, e-i]]
end

#puts rotations(3, 0, 4).map { |c| "(#{c.join(', ')})"}.join(' ')
matrix = [
  ["00", "01", "02", "03", "04"],
  ["10", "11", "12", "13", "14"],
  ["20", "21", "22", "23", "24"],
  ["30", "31", "32", "33", "34"],
  ["40", "41", "42", "43", "44"]
]

print_matrix(matrix)
