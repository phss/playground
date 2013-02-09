require File.dirname(__FILE__) + "/../src/unsorted_array_dictionary"
require File.dirname(__FILE__) + "/common_dictionary_spec"

describe UnsortedArrayDictionary do
  
  before(:each) do
   @dictionary = UnsortedArrayDictionary.new
  end
  
  it_should_behave_like "dictionary"
  
end