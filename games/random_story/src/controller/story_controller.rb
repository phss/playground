class StoryController
  
  def initialize(ui)
    @ui = ui
  end

  # TODO needs spec
  def play(story)
    enact(story) while !story.finished?
  end
  
  def enact(story)
    @ui.show(story.current_situation)
    action = @ui.select_action_from(story.current_situation.actions)
    story.current_situation.execute(action, story)
    
    #TODO quick hack
    story.unseen_events.each { |event| puts event }
    story.clear_unseen_events
    puts ""
  end
  
end