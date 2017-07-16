#!/usr/bin/env python

from dice import *

if __name__ == '__main__':
  dices = FateDiceHand()
  p1_result = dices.roll()
  p2_result = dices.roll()

  if p1_result > p2_result:
    print "P1 wins"
  elif p2_result > p1_result:
    print "P2 wins"
  else:
    print "Draw"
