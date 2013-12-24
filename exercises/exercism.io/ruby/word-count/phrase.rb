class Phrase

  def initialize(raw_phrase)
    @raw_phrase = raw_phrase
  end

  def word_count
    { @raw_phrase => 1 }
  end

end
