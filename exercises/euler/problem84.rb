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


roller = DiceRoller.new(6)
board = Board.new(40)
positions = [0]

100.times do
  current = positions.last
  positions << board.position_from(current, roller.roll(2))
end

puts positions
