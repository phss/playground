require File.dirname(__FILE__) + '/../spec_helper'

describe Story do
  
  before(:each) do
    @situations = [
      Situation.new(:first, "First situation"),
      Situation.new(:second, "Second situation"),
    ]
    @story = Story.new(@situations)
  end
  
  describe "(new)" do
    it "should have first element as the current situation" do
      @story.situations.keys.should include(:first, :second)
      @story.current_situation.should == @situations.first
    end
    
    it "should not be finished" do
      @story.finished?.should == false
    end
    
    it "should have no events or unseen events" do
      @story.events.should be_empty
      @story.unseen_events.should be_empty
    end
  end
  
  
  describe "(actions)" do
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
  end
  
end