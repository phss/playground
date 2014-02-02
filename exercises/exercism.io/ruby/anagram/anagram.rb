class Anagram

  def initialize(word)
    @word = word
  end

  def match(candidates)
    candidates.select do |candidate|
      word_not(candidate) && anagram?(candidate)
    end
  end

 private
 
  def anagram?(another_word)
    sorted_chars(@word) == sorted_chars(another_word)
  end

  def sorted_chars(string)
    string.downcase.chars.sort
  end

  def word_not(another_word)
    @word.casecmp(another_word) != 0
  end
  
end
