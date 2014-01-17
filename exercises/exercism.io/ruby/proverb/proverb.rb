class Proverb

  def initialize(*args)
    options = args.last.is_a?(Hash) ? args.pop : {}
    @words = args
    @final_consequence = options[:qualifier] ? options[:qualifier] + " " + @words.first : @words.first
  end

  def to_s
    verses = @words.each_cons(2).map do |(wanted, lost)|
      "For want of a #{wanted} the #{lost} was lost."
    end
    verses << "And all for the want of a #{@final_consequence}."
    verses.join("\n")
  end

end
