class Story
  
  attr_reader :name, :current_situation, :situations, :events, :unseen_events
  
  def initialize(name)
    @name = name
    @situations = {}
    @current_situation = nil
    @events = []
    clear_unseen_events
    @is_finished = false
  end
  
  # TODO refactor this method. Could use situations << situation
  def add_situation(situation)
    @situations[situation.name] = situation
    @current_situation = situation if @situations.size == 1
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