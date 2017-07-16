#!/usr/bin/env python

import random

class Dice(object):
  def roll(self):
    return random.randint(-1, 1)


if __name__ == '__main__':
  dice = Dice()
  print dice.roll()
