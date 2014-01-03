
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

puts k_product_sum([2, 2, 2])

upper = 6
ks = {}
#for_all_permutations do |permutation|
#end
