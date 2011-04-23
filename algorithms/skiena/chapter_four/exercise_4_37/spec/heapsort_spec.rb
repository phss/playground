require File.dirname(__FILE__) + "/../src/heapsort"
require File.dirname(__FILE__) + "/common_sort_spec"

describe Heapsort do
  before(:each) do
    @sorter = Heapsort.new
  end

  it_should_behave_like "sorting"
end
