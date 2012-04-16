class CombatScribe

  def initialize
    @turn_number = 1
    @descriptions = DescriptionScribe.new('./scribe/descriptions.rb')
  end
  
  def begin_turn
    puts_header "Turn #@turn_number"
    @turn_number += 1
  end 

  def register_attack(attacker, defender, type, damage)
    if damage > 0
      puts "#{defender.name} was mauled by #{damage} points by #{attacker.name}."
    else
      # puts DodgedDescription.for(attacker, defender, type)
      puts @descriptions.generate(:dodging_attack, :attacker => attacker, :defender => defender, :attack => type)
    end 
  end

  def health_status_of(fighter1, fighter2)
    status_print = lambda { |f| "#{f.name} has #{f.health} hit points." }
    puts status_print.call(fighter1) + ' ' + status_print.call(fighter2)
  end

  def finish_combat_between(fighter1, fighter2)
    puts_header "THE END"
    puts "#{fighter1.name} is dead" unless fighter1.alive?
    puts "#{fighter2.name} is dead" unless fighter2.alive? 
  end

 private

  def puts_header(text)
    puts "\n--- #{text} ---"
  end
end