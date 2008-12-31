class CLUI
  
  def show(situation)
    puts "\"#{situation.description}\""
  end
  
  def select_action_for(situation)
    puts "What will you do?"
    situation.actions.each { |action| puts "- #{action.capitalize}" }
    action = gets.chomp while !situation.actions.include?(action)
    return action.to_sym
  end
  
end