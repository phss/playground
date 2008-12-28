require File.dirname(__FILE__) + "/../src/binary_tree_dictionary"
require File.dirname(__FILE__) + "/common_dictionary_spec"

describe BinaryTreeDictionary do
  
  before(:each) do
   @dictionary = BinaryTreeDictionary.new
  end
  
  it_should_behave_like "dictionary"
  
end