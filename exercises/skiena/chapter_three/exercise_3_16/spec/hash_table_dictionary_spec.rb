require File.dirname(__FILE__) + "/../src/hash_table_dictionary"
require File.dirname(__FILE__) + "/common_dictionary_spec"

describe HashTableDictionary do
  
  before(:each) do
   @dictionary = HashTableDictionary.new(100)
  end
  
  it_should_behave_like "dictionary"
  
end