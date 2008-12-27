shared_examples_for "dictionary" do
    
  describe "(empty)" do
    it "should return nil when searching" do
      @dictionary.search("something").should == nil
    end
    
    it "should successfully insert item" do
      @dictionary.insert(AN_ITEM)
      @dictionary.search(AN_ITEM.key).should == AN_ITEM
    end
    
    it "should not have a min or max item" do
      @dictionary.min.should == nil
      @dictionary.max.should == nil
    end
  end  
       

  describe "(one item)" do
    
    before(:each) do
      @dictionary.insert(AN_ITEM)
    end
    
    it "should successfully find the only item" do
      @dictionary.search(AN_ITEM.key).should == AN_ITEM
    end
    
    it "should return nil when searching item not in dictionary" do
      @dictionary.search("key not in dict").should == nil
    end
    
    it "should successfully insert a new item" do
      @dictionary.insert(ANOTHER_ITEM)
      @dictionary.search(ANOTHER_ITEM.key).should == ANOTHER_ITEM
    end
    
    it "should delete the only item" do
      @dictionary.delete(AN_ITEM)
      @dictionary.search(AN_ITEM.key).should == nil
    end
    
    it "should not remove the only item when deleting an inexistant item" do
      @dictionary.delete(ANOTHER_ITEM)
      @dictionary.search(AN_ITEM.key).should == AN_ITEM
    end
    
    it "should have min and max as the only item" do
      @dictionary.min.should == AN_ITEM
      @dictionary.max.should == AN_ITEM
    end
    
    it "should not have successor or predecessor for the only item" do
      @dictionary.successor(AN_ITEM).should == nil
      @dictionary.predecessor(AN_ITEM).should == nil
    end
  end

  describe "(a few items)" do
    
    before(:each) do
      @dictionary.insert(AN_ITEM)
      @dictionary.insert(YET_ANOTHER_ITEM)
      @dictionary.insert(ANOTHER_ITEM)
    end
    
    it "should successfully find the all items" do
      @dictionary.search(AN_ITEM.key).should == AN_ITEM
      @dictionary.search(ANOTHER_ITEM.key).should == ANOTHER_ITEM
      @dictionary.search(YET_ANOTHER_ITEM.key).should == YET_ANOTHER_ITEM
    end
    
    it "should return nil when searching item not in dictionary" do
      @dictionary.search(ITEM_NOT_FOUND.key).should == nil
    end    
    
    it "should have min as the smaller key" do
      @dictionary.min.should == AN_ITEM
      @dictionary.delete(AN_ITEM)
      @dictionary.insert(AN_ITEM)
      @dictionary.min.should == AN_ITEM
    end
    
    it "should have min as the second smaller, when deleting smallest key" do
      @dictionary.delete(AN_ITEM)
      @dictionary.min.should == ANOTHER_ITEM
    end
    
    it "should have max as the larger key" do
      @dictionary.max.should == YET_ANOTHER_ITEM
      @dictionary.delete(YET_ANOTHER_ITEM)
      @dictionary.insert(YET_ANOTHER_ITEM)
      @dictionary.max.should == YET_ANOTHER_ITEM
    end
    
    it "should have max as the second largest, when deleting largest key" do
      @dictionary.delete(YET_ANOTHER_ITEM)
      @dictionary.max.should == ANOTHER_ITEM
    end
    
    it "should traverse the dictionary in order through min and successor" do
      item = @dictionary.min
      item.should == AN_ITEM
      item = @dictionary.successor(item)
      item.should == ANOTHER_ITEM
      item = @dictionary.successor(item)
      item.should == YET_ANOTHER_ITEM
      @dictionary.successor(item).should == nil      
    end
    
    it "should traverse the dictionary in reverse order through max and predecessor" do
      item = @dictionary.max
      item.should == YET_ANOTHER_ITEM
      item = @dictionary.predecessor(item)
      item.should == ANOTHER_ITEM
      item = @dictionary.predecessor(item)
      item.should == AN_ITEM
      @dictionary.predecessor(item).should == nil      
    end    
  end

end