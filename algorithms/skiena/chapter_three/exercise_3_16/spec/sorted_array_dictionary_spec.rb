require File.dirname(__FILE__) + "/../src/sorted_array_dictionary"
require File.dirname(__FILE__) + "/common_dictionary_spec"

describe SortedArrayDictionary do
  
  before(:each) do
   @dictionary = SortedArrayDictionary.new
  end
  
  it_should_behave_like "dictionary"
  
end