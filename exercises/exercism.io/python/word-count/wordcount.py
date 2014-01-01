from collections import Counter
import re

class Phrase(object):

  def __init__(self, string):
    self.string = string

  def word_count(self):
    occurences = Counter()
    for word in self.__normalised_words():
      occurences[word] += 1
    return occurences

  def __normalised_words(self):
    normalised_string = self.string.lower()
    return re.compile("[\w']+").findall(normalised_string)
