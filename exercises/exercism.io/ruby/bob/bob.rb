class Bob

  def hey(raw_phrase)
    phrase = raw_phrase.gsub("\n", '')
    if shouting?(phrase)
      'Woah, chill out!'
    elsif question?(phrase)
      'Sure.'
    elsif quiet?(phrase)
      'Fine. Be that way!'
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

  def quiet?(phrase)
    phrase.strip == ''
  end

end
