

def frequency(array)
  array.inject(Hash.new(0)) { |freq, elem| freq[elem] += 1; freq }
end

lines = File.readlines('files/problem6.txt').map(&:chomp)

data = Array.new(8) { [] }

lines.each do |line|
  line.chars.each_with_index do |char, i|
    data[i] << char
  end
end

word = data.map do |d|
  frequency(d).sort_by { |_, freq| freq }.first.first
end.join

puts word
