class Fighter
  attr_reader :name, :health

  def initialize(name, health, attack, defence)
    @name, @health, @attack, @defence = name, health, attack, defence    
  end

  def roll_attack
    Dice.roll(6) + @attack
  end

  def roll_defence
    Dice.roll(6) + @defence
  end

  def take_damage(damage)
    @health -= damage
  end

  def alive?
    @health > 0
  end
end