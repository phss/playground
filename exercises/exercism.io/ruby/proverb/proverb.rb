class Proverb

  def initialize(*words)
    @words = words 
  end

  def to_s
    "For want of a #{@words.first} the #{@words.last} was lost.\nAnd all for the want of a #{@words.first}."
  end

end
