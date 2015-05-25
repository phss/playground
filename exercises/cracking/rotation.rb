def print_matrix(m)
  m.each do |row|
    puts row.join(' ')
  end
end

def rotations(i, s, e)
  [[i, s], [e, i], [e-i+s, e], [s, e-i+s]]
end

def apply_rotations(m, rots)
  values = rots.map { |r, c| m[c][r] }.rotate(-1) 
  rots.zip(values).each do |rot, value|
    r, c = rot
    m[c][r] = value
  end
end

def rotate_90(m)
  0.upto(m.size/2) do |s|
    e = m.size - 1 - s
    s.upto(e-1) do |i|
      rots = rotations(i, s, e)
      apply_rotations(m, rots)
    end
  end
end

puts rotations(1, 1, 3).map { |c| "(#{c.join(', ')})"}.join(' ')

matrix = [
  ["00", "01", "02", "03", "04"],
  ["10", "11", "12", "13", "14"],
  ["20", "21", "22", "23", "24"],
  ["30", "31", "32", "33", "34"],
  ["40", "41", "42", "43", "44"]
]

print_matrix(matrix)

#apply_rotations(matrix, rotations(0, 0, 4))
rotate_90(matrix)
puts

print_matrix(matrix)

