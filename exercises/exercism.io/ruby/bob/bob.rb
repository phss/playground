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
    /[A-Z]/.match(phrase) && !/[a-z]/.match(phrase)
  end

  def question?(phrase)
    /\?$/.match(phrase)
  end

end
