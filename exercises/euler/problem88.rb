
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

def k_product_sum(numbers)
  product = numbers.reduce(&:*) 
  sum = numbers.reduce(&:+) 

  return numbers.size + product - sum
end

upper = 15
ks = {}
counter = 0
for_all_permutations do |permutation|
  break if counter > 10000
  counter += 1

  k = k_product_sum(permutation)
  product = permutation.reduce(&:*)
  if k <= upper && (!ks.has_key?(k) || ks[k] > product)
    ks[k] = product
  end
end

puts ks.size
puts ks.values.sort.join(', ')
puts ks.values.uniq.reduce(&:+)
