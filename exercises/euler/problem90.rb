
numbers = (0..9).to_a

permutations = numbers.permutation(6).to_a.map(&:sort).uniq

puts permutations.count
