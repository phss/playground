upper = 10001

def next_square_chain(number)
  digits = number.to_s.chars.map(&:to_i)
  return digits.map { |d| d*d }.reduce(&:+)
end

puts next_square_chain(32)
