class DiceRoller
  def initialize(sides)
    @sides = sides    
  end

  def roll(dices)
    rolls = dices.times.map { rand(@sides) + 1 }
    rolls.reduce(:+)
  end
end

class Board
  def initialize(size)
    @size = size
  end

  def position_from(start_position, dice_roll)
    (start_position + dice_roll) % @size
  end
end

def frequency(array)
  array.group_by { |obj| obj }
       .map { |k, v| [k, v.size / array.size.to_f] }
       .sort_by { |obj| obj.first }
end

roller = DiceRoller.new(6)
board = Board.new(40)
positions = [0]

10000.times do
  current = positions.last
  positions << board.position_from(current, roller.roll(2))
end

puts frequency(positions).map { |pos_freq| pos_freq.join(" -> ") }
