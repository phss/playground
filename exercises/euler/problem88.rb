
def for_all_permutations
  current = (2..9).map { |i| [i] } 
  while true
    next_permutation = []
    current.each do |permutation|
      (permutation.last..9).each do |i|
        new_permutation = permutation + [i]
        yield new_permutation
        next_permutation << new_permutation
      end
    end

    current = next_permutation
  end
end

counter = 0
for_all_permutations do |permutation|
  puts permutation.join
  if counter > 100
    break
  end
  counter += 1
end
