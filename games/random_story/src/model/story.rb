class Story
  
  attr_reader :current_situation, :situations, :events, :unseen_events
  
  def initialize(situations)
    @situations = situations.inject({}) { |result, situation| result[situation.name] = situation; result }
    @current_situation = situations.first
    @events = []
    clear_unseen_events
    @is_finished = false
  end
    
  def go_to(situation)
    @current_situation = @situations[situation]
  end
  
  def log(event)
    @events << event
    @unseen_events << event
  end
  
  def clear_unseen_events
    @unseen_events = []
  end
  
  def end
    @is_finished = true
  end
  
  def finished?
    @is_finished
  end
  
end