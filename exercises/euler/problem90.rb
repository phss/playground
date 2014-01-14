numbers = (0..9).to_a
dices = numbers.permutation(6).to_a.map(&:sort).uniq.map do |dice|
  if dice.include? 6
    dice << 9
  elsif dice.include? 9
    dice << 6
  end
  dice
end

SQUARES = (1..9).map { |n| (n*n) }

def square_dices?(dice_1, dice_2)
  combs1 = dice_1.product(dice_2).map(&:join).map(&:to_i)
  combs2 = dice_2.product(dice_1).map(&:join).map(&:to_i)
  return (SQUARES - combs1 - combs2).empty?
end

count = 0
(0..dices.size-2).each do |i|
  (i+1..dices.size-1).each do |j|
    dice_1 = dices[i]
    dice_2 = dices[j]
    if square_dices?(dice_1, dice_2)
      count +=1
    end
  end
end
puts count
