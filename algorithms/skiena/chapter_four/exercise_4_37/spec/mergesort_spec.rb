require File.dirname(__FILE__) + "/../src/mergesort"
require File.dirname(__FILE__) + "/common_sort_spec"

describe Mergesort do
  before(:each) do
    @sorter = Mergesort.new
  end

  it_should_behave_like "sorting"
end
