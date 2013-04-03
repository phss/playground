KPARTS = {}

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
  
end

puts partitions(5)
