class Bob(object):

  def hey(self, phrase):
    utterance = Utterance(phrase)

    if utterance.is_silence():
        return 'Fine. Be that way!'
    elif utterance.is_a_shout():
        return 'Woah, chill out!'
    elif utterance.is_a_question():
        return 'Sure.'
    else:
        return 'Whatever.'


class Utterance(object):

  def __init__(self, phrase):
    self.phrase = phrase

  def is_silence(self):
    return not self.phrase.strip()

  def is_a_question(self):
    return self.phrase.endswith('?')

  def is_a_shout(self):
    return self.phrase.isupper()
