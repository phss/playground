class Anagram

  def initialize(word)
    @word = word
  end

  def match(candidates)
    candidates.select do |candidate|
      lower_candidate = candidate.downcase
      lower_word = @word.downcase
      lower_word.chars.sort == lower_candidate.chars.sort && lower_word != lower_candidate
    end
  end
  
end
