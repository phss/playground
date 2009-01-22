require File.dirname(__FILE__) + '/../spec_helper'

describe Story do
  
  before(:each) do
    @story = Story.new("Story name")
  end
  
  describe "(new)" do
    it "should have no the current situation" do
      @story.current_situation.should == nil
    end
    
    it "should not be finished" do
      @story.finished?.should == false
    end
    
    it "should have no events or unseen events" do
      @story.events.should be_empty
      @story.unseen_events.should be_empty
    end
    
    it "should have a name" do
      @story.name.should == "Story name"
    end
  end
  
  describe "(actions)" do
    
    before(:each) do
      @story.add_situation(Situation.new(:first, "First situation"))
      @story.add_situation(Situation.new(:second, "Second situation"))
    end
    
    it "should end a story" do
      @story.finished?.should == false
      @story.end
      @story.finished?.should == true
    end
    
    it "should be able to go to another situation" do
      @story.current_situation.name.should == :first
      @story.go_to(:second)
      @story.current_situation.name.should == :second
    end
    
    it "should log an event" do
      @story.events.should be_empty
      @story.unseen_events.should be_empty
      event = "Something happened"
      @story.log(event)
      @story.events.should == [event]
      @story.unseen_events.should == [event]
    end
    
    it "should clear unseen events" do
      @story.log("something")
      @story.clear_unseen_events
      @story.unseen_events.should be_empty
    end
    
    it "should end a story with a final event" do
      @story.finished?.should == false
      @story.unseen_events.should be_empty
      @story.end_with("Finished story")
      @story.finished?.should == true
      @story.unseen_events.should == ["Finished story"]
    end
  end
  
end