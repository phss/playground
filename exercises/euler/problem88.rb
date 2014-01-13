
def for_all_factors(upper_bound)
  current_level = (2..Math.sqrt(upper_bound).ceil).map { |i| [i] } 

  while !current_level.empty?
    next_level = []

    current_level.each do |factor|
      current_prod = factor.reduce(&:*)
      current_upper_bound = (upper_bound / current_prod).ceil

      (factor.last..current_upper_bound).each do |i|
        new_factor = factor + [i]
        yield new_factor
        next_level << new_factor
      end
    end

    current_level = next_level
  end
end

def k_product_sum(numbers)
  product = numbers.reduce(&:*) 
  sum = numbers.reduce(&:+) 

  return numbers.size + product - sum
end

upper_k = 12000
ks = {}

for_all_factors(upper_k*2) do |factor|
  k = k_product_sum(factor)
  product = factor.reduce(&:*)
  if k <= upper_k && (!ks.has_key?(k) || ks[k] > product)
    ks[k] = product
  end
end

puts ks.size
puts ks.values.uniq.reduce(&:+)
