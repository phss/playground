#!/usr/bin/env python

import random
from collections import Counter

class Dice(object):
  def roll(self):
    return random.randint(-1, 1)

class FateDiceHand(object):
  def __init__(self):
    self.dice = Dice()

  def roll(self):
    result = 0
    for _ in xrange(4):
      result += self.dice.roll()
    return result


if __name__ == '__main__':
  dice = FateDiceHand()
  results = []
  for _ in xrange(10000):
    results.append(dice.roll())

  freqs = Counter(results)
  print freqs
