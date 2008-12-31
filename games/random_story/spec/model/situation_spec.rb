require File.dirname(__FILE__) + '/../spec_helper'

SAMPLE_NAME = :sample_name
SAMPLE_DESCRIPTION = "This is a sample description"

describe Situation do
  
  before(:each) do
    @situation = Situation.new(SAMPLE_NAME, SAMPLE_DESCRIPTION)
  end
  
  it "should have a name, description and empty actions at creation" do
    @situation.name.should == SAMPLE_NAME
    @situation.description.should == SAMPLE_DESCRIPTION
    @situation.actions.should be_empty
  end
  
  it "should have an action after adding it" do
    @situation.actions.should be_empty
    @situation.add_action(:blah) { }
    @situation.actions.should include("blah")
  end
  
  it "should throw exception when executing unknown action" do
    lambda { @situation.execute(:unknown_action, nil) }.should raise_error
  end
  
  it "should execute proc for added action" do
    story = mock("story")
    story.should_receive(:log).with("Done!")
    
    @situation.add_action(:do_it) { |story| story.log("Done!") }
    @situation.execute(:do_it, story)
  end
  
end