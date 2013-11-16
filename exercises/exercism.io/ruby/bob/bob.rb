class Bob

  def hey(multiline_phrase)
    conversation = Conversation.new(multiline_phrase)

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

 private

end

class Conversation

  def initialize(multiline_phrase)
    @phrase = multiline_phrase.gsub("\n", '')
  end

  def shouting?
    /[[:upper:]]/.match(@phrase) && !/[[:lower:]]/.match(@phrase)
  end

  def question?
    /\?$/.match(@phrase)
  end

  def quiet?
    @phrase.strip == ''
  end

end
