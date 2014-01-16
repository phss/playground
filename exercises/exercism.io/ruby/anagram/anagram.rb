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
    keify(@word) == keify(another_word)
  end

  def keify(string)
    string.downcase.chars.sort
  end

  def word_not(another_word)
    @word.casecmp(another_word) != 0
  end
  
end
