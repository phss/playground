class Phrase

  def initialize(raw_phrase)
    @raw_phrase = raw_phrase
  end

  def word_count
    words = @raw_phrase.split
    words.frequency
  end

end

module Enumerable

  def frequency
    inject(Hash.new(0)) { |hash, elem| hash[elem] += 1; hash }
  end

end
