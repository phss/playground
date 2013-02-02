COINS = [200, 100, 50, 20, 10, 5, 2, 1]

def ways_to_make(amount)
  ways = Array.new(amount + 1, 0)
  ways[0] = 1 # Kind of a hack to only count first coin once

  COINS.each do |coin|
    (amount/coin).times do |i|
      idx = (i+1)*coin      
      ways[idx] += ways[idx - coin]
    end    
  end

  return ways[amount]
end

puts ways_to_make(200)
