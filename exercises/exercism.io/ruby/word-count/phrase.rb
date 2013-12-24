class Phrase

  def initialize(raw_phrase)
    @raw_phrase = raw_phrase
  end

  def word_count
    words = @raw_phrase.split
    words.inject(Hash.new(0)) { |hash, word| hash[word] += 1; hash }
  end

end
