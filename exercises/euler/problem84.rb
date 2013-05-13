
class DiceRoller
  def initialize(sides)
    @sides = sides    
  end

  def roll(dices)
    rolls = dices.times.map { rand(@sides) + 1 }
    rolls.reduce(:+)
  end
end


roller = DiceRoller.new(6)
10.times { puts roller.roll(2) }
