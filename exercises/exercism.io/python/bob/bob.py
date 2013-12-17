class Bob(object):
    
  def hey(self, phrase):
    conversation = Conversation(phrase)

    if conversation.is_quiet():
        return 'Fine. Be that way!'
    elif conversation.is_a_question():
        return 'Sure.'
    else:
        return 'Whatever.'


class Conversation(object):

  def __init__(self, phrase):
    self.phrase = phrase

  def is_quiet(self):
    return not self.phrase.strip()
    
  def is_a_question(self):
    return self.phrase.endswith('?')
