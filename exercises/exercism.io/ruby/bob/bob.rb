class Bob

  def hey(phrase)
    conversation = Conversation.new(phrase)

    if conversation.shouting?
      'Woah, chill out!'
    elsif conversation.question?
      'Sure.'
    elsif conversation.quiet?
      'Fine. Be that way!'
    else
      'Whatever.'
    end
  end

end

class Conversation

  def initialize(phrase)
    @phrase = phrase
  end

  def shouting?
    matches(/[[:upper:]]/) && !matches(/[[:lower:]]/)
  end

  def question?
    @phrase.end_with?('?')
  end

  def quiet?
    @phrase.strip.empty?
  end

 private

  def matches(regexp)
    regexp.match(@phrase)
  end

end
