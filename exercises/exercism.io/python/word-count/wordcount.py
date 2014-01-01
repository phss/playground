from collections import Counter
import re

class Phrase(object):

  def __init__(self, string):
    self.string = string

  def word_count(self):
    return Counter(self.__normalised_words())

  def __normalised_words(self):
    normalised_string = self.string.lower()
    return re.compile("[\w']+").findall(normalised_string)
