class Proverb

  def initialize(*words)
    @words = words 
  end

  def to_s
    first = "For want of a #{@words.first} the #{@words.last} was lost."
    ending = "And all for the want of a #{@words.first}."
    [first, ending].join("\n")
  end

end
