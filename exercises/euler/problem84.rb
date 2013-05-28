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

  def initialize
    @size = 40
    @positions = {
      :go => [0],
      :jail => [10],
      :gtj => [30],
      :c1 => [11],
      :e3 => [24],
      :h2 => [39],
      :r1 => [5],
      :next_r => [5, 15, 25, 35],
      :next_u => [12, 28],
      :chest => [2, 17, 33],
      :chance => [7, 22, 36]
    }
  end

  def position_from(start_position, dice_roll)
    (start_position + dice_roll) % @size
  end

  def position_from_card(position, card)
    if card == :back_3
      position_from(position, -3)
    elsif @positions.key? card
      card_positions = @positions[card]
      next_positions = card_positions.select { |card_pos| card_pos > position} << card_positions.first
      next_positions.first
    end
  end

  def go_to_jail?(position)
    @positions[:gtj].first == position
  end

  def jail
    @positions[:jail].first
  end

  def community_chest?(position)
    @positions[:chest].include? position
  end

  def chance?(position)
    @positions[:chance].include? position
  end

  def draw_card?(position)
    community_chest?(position) || chance?(position)
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
       .sort_by { |obj| obj.last }
end

roller = DiceRoller.new(6)
board = Board.new
chest_cards = Cards.new([:go, :jail, :nop, :nop, :nop, :nop, :nop, :nop, :nop, :nop, :nop, :nop, :nop, :nop, :nop, :nop])
chance_cards = Cards.new([:go, :jail, :c1, :e3, :h2, :r1, :next_r, :next_r, :next_u, :back_3, :nop, :nop, :nop, :nop, :nop, :nop])
positions = [0]

1000000.times do
  current = positions.last
  positions << next_pos = board.position_from(current, roller.roll(2))

  if board.go_to_jail?(next_pos) || roller.three_doubles?
    positions << board.jail
  elsif board.draw_card?(next_pos)
    cards = board.community_chest?(next_pos) ? chest_cards : chance_cards
    card = cards.draw
    positions << board.position_from_card(next_pos, card) unless card == :nop
  end
end

puts frequency(positions).map { |pos_freq| pos_freq.join(" -> ") }
