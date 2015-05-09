def sum_of_divisors_up_to(n)
  sums = Array.new(n+1) { 0 }

  1.upto(n/2) do |num|
    2.upto(n/num) do |multi|
      next_divisible = multi * num
      sums[next_divisible] += num
    end
  end

  return sums
end

def initialize_chain_array(sums)
  a = Array.new(sums.size) 
  a[0] = -1
  a[1] = -1

  sums.each_with_index do |sum, i|
    a[i] = -1 if sum > 1000000
  end

  return a
end

def has_no_duplicates(chain)
  chain.size == chain.uniq.size
end

sums = sum_of_divisors_up_to(1000000)
chain_array = initialize_chain_array(sums)

2.upto(1000000) do |i|
  chain = [i]
  while chain_array[chain.last].nil? && has_no_duplicates(chain)
    chain << sums[chain.last]
  end

  if chain_array[chain.last] == -1 || has_no_duplicates(chain) # Not a chain
    chain.pop
    chain.each { |c| chain_array[c] = -1 }
  else
    closed_chain_start = chain.index(chain.last)
    chain.pop

    if closed_chain_start > 0 # Chain doesn't start on first element
      chain[0..closed_chain_start-1].each { |c| chain_array[c] = -1 }
    end

    closed_chain = chain[closed_chain_start..chain.size-1]
    closed_chain.each { |c| chain_array[c] = closed_chain.size }
  end
end

puts chain_array.index(chain_array.max)
