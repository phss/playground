class Anagram

  def initialize(word)
    @word = word
  end

  def match(candidates)
    candidates.select do |candidate|
      lower_candidate = candidate.downcase
      lower_word = @word.downcase
      anagram?(candidate) && lower_word != lower_candidate
    end
  end

 private
 
  def anagram?(another_word)
    keify(@word) == keify(another_word)
  end

  def keify(string)
    string.downcase.chars.sort
  end
  
end
