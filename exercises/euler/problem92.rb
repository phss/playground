upper = 100000

def next_square_chain(number)
  digits = number.to_s.chars.map(&:to_i)
  return digits.map { |d| d*d }.reduce(&:+)
end

arrives = []
arrives[1] = 1
arrives[89] = 89

(1..upper).each do |i|
  chain = [i]
  while arrives[chain.last].nil?
    chain << next_square_chain(chain.last)
  end
  a = arrives[chain.last]
  chain.each do |c|
    arrives[c] = a
  end
end

puts arrives[0..upper].count { |n| n == 89 }
