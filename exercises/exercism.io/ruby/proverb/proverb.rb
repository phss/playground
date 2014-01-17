class Proverb

  def initialize(*words)
    @words = words 
  end

  def to_s
    verses = []
    @words.each_cons(2) do |(wanted, lost)|
      verses << "For want of a #{wanted} the #{lost} was lost."
    end
    verses << "And all for the want of a #{@words.first}."
    verses.join("\n")
  end

end
