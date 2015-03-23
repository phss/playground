dice_options = (1..7).to_a

probabilities = Array.new(36, 0)
all_permutations = dice_options.repeated_permutation(5)


all_permutations.each do |combination|
  sum = combination.inject { |a, b| a + b }
  probabilities[sum] += 1
end

odds_on_5 = Array.new(5, 0)

probabilities.each_with_index do |prob, i|
  odds_on_5[i%5] += prob
end

odds_on_5.each_with_index do |odd, i|
  prob = odd / all_permutations.size.to_f * 100
  puts "#{i+1}: #{prob}%"
end
