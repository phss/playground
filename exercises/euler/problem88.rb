
def for_all_permutations(max, levels)
  current = (2..Math.sqrt(max).ceil).map { |i| [i] } 
  while current.size > 0 && current.first.size <= levels
    next_permutation = []
    current.each do |permutation|
      prod = permutation.reduce(&:*)
      new_max = (max / prod).ceil
      (permutation.last..new_max).each do |i|
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

upper = 12000
ks = {}

for_all_permutations(upper*2, 14) do |permutation|
  k = k_product_sum(permutation)
  product = permutation.reduce(&:*)
  if k <= upper && (!ks.has_key?(k) || ks[k] > product)
    ks[k] = product
  end
end

puts ks.size
#puts ks.values.sort.join(', ')
puts ks.values.uniq.reduce(&:+)
