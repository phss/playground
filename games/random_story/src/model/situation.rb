class Situation
  attr_reader :name
  attr_accessor :description
  
  def initialize(name, description)
    @name = name
    @description = description
    @actions = {}
  end

  def actions
    @actions.keys.map { |action_name| action_name.to_s }
  end  

  def add_action(action, &action_block)
    @actions[action] = action_block
  end
  
  def execute(action, story)
    raise Exception.new, "No action with name #{action}." unless @actions.include?(action)
    
    @actions[action].call(story)
  end
end