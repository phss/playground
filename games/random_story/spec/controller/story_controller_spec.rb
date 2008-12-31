require File.dirname(__FILE__) + '/../spec_helper'

describe StoryController do
  
  before(:each) do
    @ui_mock = mock("ui")
    @controller = StoryController.new(@ui_mock)
  end
  
  it "should show current situation and run action when enacting a story" do
    @ui_mock.should_receive(:show).with(SAMPLE_SITUATION)
    @ui_mock.should_receive(:select_action_for).with(SAMPLE_SITUATION).and_return(:sample_action)
    
    sample_story = Story.new([SAMPLE_SITUATION])
    sample_story.add_situation(SAMPLE_SITUATION)
    @controller.enact(sample_story)
  end
  
end