require File.dirname(__FILE__) + "/cl_stub"
require File.dirname(__FILE__) + '/../spec_helper'

describe CLUI do
  
  before(:each) do
    @ui = CLUI.new
    @@output.clear
  end
  
  it "should show situation description" do
    @ui.show(SAMPLE_SITUATION)
    @@output.shift.should == "\"#{SAMPLE_SITUATION.description}\""
  end
  
  it "should show actions and select an option" do
    @@input << "Wrong option"
    @@input << "1"
    
    @ui.select_action_from(SAMPLE_SITUATION.actions).should == SAMPLE_SITUATION.actions.first.to_sym
  end
  
end