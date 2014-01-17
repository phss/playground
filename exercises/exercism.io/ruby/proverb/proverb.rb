class Proverb

  def initialize(*args)
    qualifier = args.last.is_a?(Hash) ? args.pop[:qualifier] : nil

    @words = args
    @final_want = @words.first.clone
    @final_want.prepend("#{qualifier} ") if qualifier
  end

  def to_s
    verses = @words.each_cons(2).map do |(want, lost)|
      "For want of a #{want} the #{lost} was lost."
    end
    verses << "And all for the want of a #@final_want."
    verses.join("\n")
  end

end
