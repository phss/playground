from collections import defaultdict
import re

class Phrase(object):

  def __init__(self, string):
    self.string = string

  def word_count(self):
    occurences = defaultdict(int)
    for word in self.__normalised_words():
      occurences[word] += 1
    return occurences

  def __normalised_words(self):
    normalised_string = self.string.lower()
    return re.compile("[\w']+").findall(normalised_string)
