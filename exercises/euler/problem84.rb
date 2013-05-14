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
  attr_reader :jail

  def initialize

    @size = 40
    @gtj_pos = 30 
    @jail = 10
  end

  def position_from(start_position, dice_roll)
    (start_position + dice_roll) % @size
  end

  def go_to_jail?(position)
    @gtj_pos == position
  end
end


def frequency(array)
  array.group_by { |obj| obj }
       .map { |k, v| [k, v.size / array.size.to_f] }
       .sort_by { |obj| obj.first }
end

roller = DiceRoller.new(6)
board = Board.new
positions = [0]

10000.times do
  current = positions.last
  positions << next_pos = board.position_from(current, roller.roll(2))

  if board.go_to_jail?(next_pos)
    positions << board.jail
  end
end

puts frequency(positions).map { |pos_freq| pos_freq.join(" -> ") }
