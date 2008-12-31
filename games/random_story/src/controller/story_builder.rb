#
# This is a really bad implementation of a DSL builder.
#
class StoryBuilder
  
  def self.make(story_file)
    @@story = @@first_situation = @@situation = nil
    instance_eval(File.read(story_file))
    return @@story
  end
  
  private
  
  def self.story(name)
    @@story = Story.new(name)
    yield
    @@story.go_to(@@first_situation) unless @@first_situation.nil?
    return @@story
  end
  
  def self.begin_at(situation_name)
    @@first_situation = situation_name
  end
  
  def self.situation(name)
    @@situation = Situation.new(name, "")
    yield
    @@story.add_situation(@@situation)
  end
  
  def self.description(description)
    @@situation.description = description
  end
  
  def self.action(name, &blk)
    @@situation.add_action(name, &blk)
  end
  
end