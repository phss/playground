# Generate all numbers where a³ + b³ = c³ + d³ and a, b, c, d < n
# Although I am lazy so I am just counting the number of different combinations

N = 1000

def brute_force
  count = 0
  0.upto(N) do |a|
    0.upto(N) do |b|
      0.upto(N) do |c|
        0.upto(N) do |d|
          #puts "#{a}, #{b}, #{c}, #{d}" i
          count += 1 if a**3 + b**3 == c**3 + d**3 
        end
      end
    end
  end
  return count
end

def sort_of_not_brute_force
  results = {}
  0.upto(N) do |a| 
    0.upto(N) do |b|
      r = a**3 + b**3
      results[r] = [] unless results.key? r
      results[r] << [a, b]
    end
  end


  count = 0
  results.each_value do |v|
    v.each do |e1|
      v.each do |e2|
        count += 1 # Should print numbers, but just lazy and counting
      end
    end
  end
  return count
end

#puts brute_force
puts sort_of_not_brute_force
