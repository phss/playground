class Combat
  
  def initialize(fighter1, fighter2)
    @fighter1, @fighter2 = fighter1, fighter2    
  end

  def self.between(fighter1, fighter2)
    Combat.new(fighter1, fighter2)
  end

  def fight
    turn_number = 1
    while @fighter1.alive? && @fighter2.alive?
      puts "--- Turn #{turn_number} ---"
      turn
      turn_number += 1
    end
    puts "--- THE END ---"
    puts "#{@fighter1.name} is dead" unless @fighter1.alive?
    puts "#{@fighter2.name} is dead" unless @fighter2.alive?
  end

  def turn
    hit(@fighter1, @fighter2)
    hit(@fighter2, @fighter1)
    display_health_status
  end

 private

  def hit(attacker, defender)
    attack_value = attacker.roll_attack
    defence_value = defender.roll_defence

    if attack_value > defence_value
      damage = attack_value - defence_value
      defender.take_damage(damage)
      puts "#{defender.name} was mauled by #{damage} points by #{attacker.name}."
    else
      puts "#{defender.name} dodged a vicious attack from #{attacker.name}."
    end
  end

  def display_health_status
    status_print = lambda { |f| "#{f.name} has #{f.health} hit points." }
    puts status_print.call(@fighter1) + ' ' + status_print.call(@fighter2)
  end

end