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

class ChainArray
  def initialize(sums)
    @chain_array = Array.new(sums.size) 
    @chain_array[0] = -1
    @chain_array[1] = -1

    sums.each_with_index do |sum, i|
      @chain_array[i] = -1 if sum > sums.size
    end
  end

  def no_value?(i)
    @chain_array[i].nil?
  end

  def no_chain?(i)
    @chain_array[i] == -1
  end

  def update(chain, value)
    chain.each { |c| @chain_array[c] = value }
  end

  def biggest_chain
    @chain_array.index(@chain_array.max) 
  end
end

def has_no_duplicates(chain)
  chain.size == chain.uniq.size
end

sums = sum_of_divisors_up_to(1000000)
chain_array = ChainArray.new(sums)

2.upto(1000000) do |i|
  chain = [i]
  while chain_array.no_value?(chain.last) && has_no_duplicates(chain)
    chain << sums[chain.last]
  end

  if chain_array.no_chain?(chain.last) || has_no_duplicates(chain) # Not a chain
    chain.pop
    chain_array.update(chain, -1)
  else
    closed_chain_start = chain.index(chain.last)
    chain.pop

    if closed_chain_start > 0 # Chain doesn't start on first element
      chain_array.update(chain[0..closed_chain_start-1], -1)
    end

    closed_chain = chain[closed_chain_start..chain.size-1]
    chain_array.update(closed_chain, closed_chain.size)
  end
end

puts chain_array.biggest_chain
