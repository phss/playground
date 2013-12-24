class Phrase

  def initialize(raw_phrase)
    @raw_phrase = raw_phrase
  end

  def word_count
    words = @raw_phrase.scan(/\w+/).map(&:downcase)
    words.frequency
  end

end

module Enumerable

  def frequency
    inject(Hash.new(0)) { |hash, elem| hash[elem] += 1; hash }
  end

end
