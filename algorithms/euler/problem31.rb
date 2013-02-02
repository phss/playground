COINS = [1, 2, 5, 10, 20, 50, 100, 200]

def ways_to_make(amount)
  ways = Array.new(amount + 1, 0)
  ways[0] = 1 # Kind of a hack to only count first coin once

  COINS.each do |coin|
    (coin..amount).each do |idx|
      ways[idx] += ways[idx - coin]
    end    
  end

  return ways[amount]
end

puts ways_to_make(2000)
