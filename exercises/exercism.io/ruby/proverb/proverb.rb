class Proverb

  def initialize(*args)
    options = args.last.is_a?(Hash) ? args.pop : {}
    @words = args
    @qualifier = options[:qualifier] ? options[:qualifier] + " " + @words.first : @words.first
  end

  def to_s
    verses = []
    @words.each_cons(2) do |(wanted, lost)|
      verses << "For want of a #{wanted} the #{lost} was lost."
    end
    verses << "And all for the want of a #{@qualifier}."
    verses.join("\n")
  end

end
