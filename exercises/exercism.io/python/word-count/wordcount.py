from collections import defaultdict

class Phrase(object):

  def __init__(self, string):
    self.string = string

  def word_count(self):
    occurences = defaultdict(int)
    for word in self.string.split():
      occurences[word] += 1
    return occurences
