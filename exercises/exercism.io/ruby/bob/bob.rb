class Bob

  def hey(phrase)
    if shouting?(phrase)
      'Woah, chill out!'
    else
      'Whatever.'
    end
  end

 private

  def shouting?(phrase)
    phrase == phrase.upcase
  end

end
