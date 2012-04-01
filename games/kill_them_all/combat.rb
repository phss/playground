class Combat
  
  def initialize(fighter1, fighter2)
    @fighter1, @fighter2 = fighter1, fighter2    
    @scribe = CombatScribe.new
  end

  def self.between(fighter1, fighter2)
    Combat.new(fighter1, fighter2)
  end

  def fight
    while @fighter1.alive? && @fighter2.alive?
      @scribe.begin_turn
      turn
    end
    @scribe.finish_combat_between(@fighter1, @fighter2)
  end

  def turn
    hit(@fighter1, @fighter2)
    hit(@fighter2, @fighter1)
    @scribe.health_status_of(@fighter1, @fighter2)
  end

 private

  def hit(attacker, defender)
    damage = attacker.roll_attack - defender.roll_defence
    defender.take_damage(damage) if damage > 0

    @scribe.register_attack(attacker, defender, :punch, damage)
  end

end