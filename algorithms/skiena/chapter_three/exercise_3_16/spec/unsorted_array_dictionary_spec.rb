require File.dirname(__FILE__) + "/common_dictionary_spec"
require File.dirname(__FILE__) + "/../src/unsorted_array_dictionary"

AN_ITEM = Item.new("a key", "a value")
ANOTHER_ITEM = Item.new("another key", "another value")
YET_ANOTHER_ITEM = Item.new("yet another key", "yet another value")
ITEM_NOT_FOUND = Item.new("not found", "not found")

describe UnsortedArrayDictionary do
  
  before(:each) do
   @dictionary = UnsortedArrayDictionary.new
  end
  
  it_should_behave_like "dictionary"
  
end