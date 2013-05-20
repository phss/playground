class DiceRoller
  def initialize(sides)
    @sides = sides    
    @consecutive_doubles = 0 # a bit of terrible state
  end

  def roll(dices)
    rolls = dices.times.map { rand(@sides) + 1 }
    @consecutive_doubles = rolls.uniq.size == 1 ? @consecutive_doubles + 1 : 0

    return rolls.reduce(:+)
  end

  def three_doubles?
    @consecutive_doubles == 3
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

class Cards
  def initialize(cards)
    @cards = cards.shuffle
  end

  def draw
    card = @cards.shift
    @cards.push(card)

    return card
  end
end

def frequency(array)
  array.group_by { |obj| obj }
       .map { |k, v| [k, v.size / array.size.to_f] }
       .sort_by { |obj| obj.first }
end

roller = DiceRoller.new(6)
board = Board.new
chest_cards = Cards.new([:go, :jail, :nop, :nop, :nop, :nop, :nop, :nop, :nop, :nop, :nop, :nop, :nop, :nop, :nop, :nop])
chance_cards = Cards.new([:go, :jail, :c1, :e3, :h2, :r1, :next_r, :next_r, :next_u, :back_3, :nop, :nop, :nop, :nop, :nop, :nop])
positions = [0]

10000.times do
  current = positions.last
  positions << next_pos = board.position_from(current, roller.roll(2))

  if board.go_to_jail?(next_pos) || roller.three_doubles?
    positions << board.jail
  end
end

puts frequency(positions).map { |pos_freq| pos_freq.join(" -> ") }
