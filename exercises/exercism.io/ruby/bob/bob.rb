class Bob

  def hey(phrase)
    if shouting?(phrase)
      'Woah, chill out!'
    elsif question?(phrase)
      'Sure.'
    else
      'Whatever.'
    end
  end

 private

  def shouting?(phrase)
    phrase == phrase.upcase
  end

  def question?(phrase)
    phrase =~ /\?$/
  end

end
