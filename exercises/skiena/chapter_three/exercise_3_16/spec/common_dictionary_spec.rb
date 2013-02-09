shared_examples_for "dictionary" do
    
  before(:each) do
    @an_item = Item.new("a key", "a value")
    @another_item = Item.new("another key", "another value")
    @yet_another_item = Item.new("yet another key", "yet another value")
    @item_not_found = Item.new("not found", "not found")
  end
    
  describe "(empty)" do
    it "should return nil when searching" do
      @dictionary.search("something").should == nil
    end
    
    it "should successfully insert item" do
      @dictionary.insert(@an_item)
      @dictionary.search(@an_item.key).should == @an_item
    end
    
    it "should not have a min or max item" do
      @dictionary.min.should == nil
      @dictionary.max.should == nil
    end
  end  
       
  
  describe "(one item)" do
    
    before(:each) do
      @dictionary.insert(@an_item)
    end
    
    it "should successfully find the only item" do
      @dictionary.search(@an_item.key).should == @an_item
    end
    
    it "should return nil when searching item not in dictionary" do
      @dictionary.search("key not in dict").should == nil
    end
    
    it "should successfully insert a new item" do
      @dictionary.insert(@another_item)
      @dictionary.search(@another_item.key).should == @another_item
    end
    
    it "should delete the only item" do
      @dictionary.delete(@an_item)
      @dictionary.search(@an_item.key).should == nil
    end
    
    it "should not remove the only item when deleting an inexistant item" do
      @dictionary.delete(@another_item)
      @dictionary.search(@an_item.key).should == @an_item
    end
    
    it "should have min and max as the only item" do
      @dictionary.min.should == @an_item
      @dictionary.max.should == @an_item
    end
    
    it "should not have successor or predecessor for the only item" do
      @dictionary.successor(@an_item).should == nil
      @dictionary.predecessor(@an_item).should == nil
    end
  end
  
  describe "(a few items)" do
    
    before(:each) do
      @dictionary.insert(@an_item)
      @dictionary.insert(@yet_another_item)
      @dictionary.insert(@another_item)
    end
    
    it "should successfully find the all items" do
      @dictionary.search(@an_item.key).should == @an_item
      @dictionary.search(@another_item.key).should == @another_item
      @dictionary.search(@yet_another_item.key).should == @yet_another_item
    end
    
    it "should return nil when searching item not in dictionary" do
      @dictionary.search(@item_not_found.key).should == nil
    end    
    
    it "should have min as the smaller key" do
      @dictionary.min.should == @an_item
      @dictionary.delete(@an_item)
      @dictionary.insert(@an_item)
      @dictionary.min.should == @an_item
    end
    
    it "should have min as the second smaller, when deleting smallest key" do
      @dictionary.delete(@an_item)
      @dictionary.min.should == @another_item
    end
    
    it "should have max as the larger key" do
      @dictionary.max.should == @yet_another_item
      @dictionary.delete(@yet_another_item)
      @dictionary.insert(@yet_another_item)
      @dictionary.max.should == @yet_another_item
    end
    
    it "should have max as the second largest, when deleting largest key" do
      @dictionary.delete(@yet_another_item)
      @dictionary.max.should == @another_item
    end
    
    it "should traverse the dictionary in order through min and successor" do
      item = @dictionary.min
      item.should == @an_item
      item = @dictionary.successor(item)
      item.should == @another_item
      item = @dictionary.successor(item)
      item.should == @yet_another_item
      @dictionary.successor(item).should == nil      
    end
    
    it "should traverse the dictionary in reverse order through max and predecessor" do
      item = @dictionary.max
      item.should == @yet_another_item
      item = @dictionary.predecessor(item)
      item.should == @another_item
      item = @dictionary.predecessor(item)
      item.should == @an_item
      @dictionary.predecessor(item).should == nil      
    end    
  end
  
  describe "(with ordering samples)" do
    before(:each) do
      @sample_items = %w{d a k c b f l e g j}.map { |e| Item.new(e, e) }
      @sample_items.each { |item| @dictionary.insert(item) }
    end
    
    it "should find all items" do
      @sample_items.each do |item|
        @dictionary.search(item.key).should == item
      end
    end
    
    it "should traverse items in order" do
      dict_item = @dictionary.min
      @sample_items.sort.each do |sample_item|
        dict_item.should == sample_item
        dict_item = @dictionary.successor(dict_item)
      end
      dict_item.should == nil
    end
    
    it "should traverse items in reverse order" do
      dict_item = @dictionary.max
      @sample_items.sort.reverse.each do |sample_item|
        dict_item.should == sample_item
        dict_item = @dictionary.predecessor(dict_item)
      end
      dict_item.should == nil
    end
    
  end
  
  describe "(with text samples)" do
    it "should have one word" do
      add_words_from_file(@dictionary, "one_word_sample.txt")
      @dictionary.min.key.should == "a"
      @dictionary.max.key.should == "a"
    end
    
    it "should have a few word" do
      add_words_from_file(@dictionary, "few_words_sample.txt")
      @dictionary.min.key.should == "a"
      @dictionary.max.key.should == "d"
    end
    
    it "should have words from wiki" do
      add_words_from_file(@dictionary, "wiki_dictionary.txt")
      @dictionary.size.should == 1258
    end
  end
 
end

def add_words_from_file(dictionary, file)
  File.open("#{File.dirname(__FILE__)}/#{file}", "r") do |f|
    f.each_line do |line| 
      line.split.each do |word| 
        dictionary.insert(Item.new(word, word)) if dictionary.search(word).nil?
      end
    end
  end
end