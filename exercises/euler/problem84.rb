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
puts board.position_from(35, roller.roll(2))
