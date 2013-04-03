KPART = {}

def k_partition(k, n)
  key = [k, n]

  unless KPART.key?(key)
    KPART[key] = 
      if (k > n) 
        0
      elsif (k == n) 
        1
      else 
        k_partition(k + 1, n) + k_partition(k, n - k)
      end
  end

  return KPART[key]
end

def partitions(n)
  max = (0.5*n).to_i
  return 1 + (1..max).map { |k| k_partition(k, n - k) }.reduce(&:+)
end

puts partitions(5)
