require File.dirname(__FILE__) + "/../src/insertion_sort"
require File.dirname(__FILE__) + "/common_sort_spec"

describe InsertionSort do
  before(:each) do
    @sorter = InsertionSort.new
  end

  it_should_behave_like "sorting"
end
