require "par_ident"

describe ParenthisisMatcher do
  it "should report () as matching" do
    ParenthisisMatcher.match("()").should == true
  end
  
  it "should report )( as not matching" do
    ParenthisisMatcher.match(")(").should == false
  end

  it "should report ((())())() as matching" do
    ParenthisisMatcher.match("((())())()").should == true
  end

  it "should report ( as not matching" do
    ParenthisisMatcher.match("(").should == false
  end

  it "should report ()) as not matching" do
    ParenthisisMatcher.match("())").should == false
  end

end
