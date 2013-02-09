require File.dirname(__FILE__) + "/../src/selection_sort"
require File.dirname(__FILE__) + "/common_sort_spec"

describe SelectionSort do
  before(:each) do
    @sorter = SelectionSort.new
  end

  it_should_behave_like "sorting"
end
