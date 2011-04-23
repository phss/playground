require File.dirname(__FILE__) + "/../src/quicksort"
require File.dirname(__FILE__) + "/common_sort_spec"

describe Quicksort do
  before(:each) do
    @sorter = Quicksort.new
  end

  it_should_behave_like "sorting"
end
