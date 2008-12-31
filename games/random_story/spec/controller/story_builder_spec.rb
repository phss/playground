require File.dirname(__FILE__) + '/../spec_helper'

describe StoryBuilder do
  
  it "should create a story from file" do
    story = StoryBuilder.make(SAMPLE_STORY_FILE)
    
    story.name.should == "Sample Story"
    story.current_situation.name.should == :outside
    story.situations.should have_key(:outside)
    story.situations.should have_key(:entrance)
  end
  
end