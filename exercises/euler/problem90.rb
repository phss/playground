numbers = (0..9).to_a
permutations = numbers.permutation(6).to_a.map(&:sort).uniq

SQUARES = (1..9).map { |n| (n*n) }

def square_dices?(dice_1, dice_2)
  combs1 = dice_1.product(dice_2).map(&:join).map(&:to_i)
  combs2 = dice_2.product(dice_1).map(&:join).map(&:to_i)
  return (SQUARES - combs1 - combs2).empty?
end

puts square_dices?([1, 2, 3, 4, 5, 6], [1, 2, 3, 4, 5, 6])
puts square_dices?([0, 5, 6, 7, 8, 9], [1, 2, 3, 4, 8, 9])
puts square_dices?([0, 5, 6, 7, 8, 9], [1, 2, 3, 4, 6, 7])
