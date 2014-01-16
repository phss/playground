class Phrase
  WORD_REGEXP = /[\w']+/

  def initialize(raw_phrase)
    @raw_phrase = raw_phrase
  end

  def word_count
    words = @raw_phrase.downcase.scan(WORD_REGEXP)
    words.frequency
  end

end

module Enumerable

  def frequency
    each_with_object(Hash.new(0)) { |elem, hash| hash[elem] += 1 }
  end

end
