require "prime"

LIMIT = 200

values = Prime.first(5000).select { |p| p <= LIMIT }
ways = Array.new(LIMIT + 1, 0)
ways[0] = 1

values.each do |value|
  (value..LIMIT).each do |idx|
    ways[idx] += ways[idx - value]

    if ways[idx] == 5000
      puts idx
      exit
    end
  end    
end

puts ways.sort.last
