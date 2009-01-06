class CLUI
  
  def show(situation)
    puts "\"#{situation.description}\""
  end
  
  def select_action_from(actions)
    puts "What will you do?"
    actions.each_with_index { |action, i| puts "[#{i+1}] #{action.capitalize}" }
    return actions[get_action_number(actions.size)].to_sym
  end
  
  private
  
  def get_action_number(size)
    while true
      print ">> "
      action = gets.chomp.to_i
      return action - 1 if action > 0 && action <= size
    end
  end
  
end