shared_examples_for "sorting" do 

  describe "(simple cases)" do
    it "should return empty array" do
      @sorter.sort([]).should == []
    end

    it "should return array with only one element" do
      @sorter.sort([1]).should == [1]
    end

    it "should return already sorted array" do
      sorted_array = [1, 2, 3, 4, 5]
      @sorter.sort(sorted_array).should == sorted_array
    end

    it "should invert inversely sorted array" do
      @sorter.sort([3, 2, 1]).should == [1, 2, 3]
    end
 
    it "should sort unsorted array" do
      @sorter.sort([3, 1, 2, 9, 4, 5]).should == [1, 2, 3, 4, 5, 9]
    end
 end

  describe "(random cases)" do
    it "should sort 10 elements" do
      sort_with_range(0..9) 
    end
  
    it "should sort 100 elements" do
      sort_with_range(0..99) 
    end
   
    it "should sort 10000 elements" do
      sort_with_range(0..9999) 
    end

    it "should sort 1000000 elements" do
      sort_with_range(0..999999) 
    end

    def sort_with_range(range)
      @sorter.sort(rand_permutation_of(range)).should == range.to_a
    end

    def rand_permutation_of(range)
      array = range.to_a
      0.upto(array.size-1) do |i|
        j = rand(array.size)
        array[i], array[j] = array[j], array[i]
      end
      return array
    end
  end

end
