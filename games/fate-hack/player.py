#!/usr/bin/env python

from StringIO import StringIO
from dice import *

class Player(object):
  def __init__(self, name, fight_attr):
    self.name = name
    self.fight_attr = fight_attr
    self.stress = [None, None]
    self.consequences = [None, None, None]
    self.alive = True

  def roll_fight(self, dice):
    return self.fight_attr + dice.roll()

  def take_hit(self, shift):
    if shift <= 0:
      return
    elif shift == 1 and self.stress[0] == None:
      self.stress[0] = "Ouch"
      return
    elif shift <= 2 and self.stress[1] == None:
      self.stress[1] = "Ouch"
      return

    for i, c in reversed(list(enumerate(self.consequences))):
      ci = (i + 1) * 2
      if c == None:
        self.consequences[i] = "Ouch"
        return self.take_hit(shift - ci)

    self.alive = False

  def __str__(self):
    desc = StringIO()
    desc.write("Player: %s (fight: %d, alive: %s)\n" % (self.name, self.fight_attr, self.alive))
    desc.write("Stress: 1pt=%s, 2pt=%s\n" % (self.stress[0], self.stress[1]))
    desc.write("Consequences: 2pt=%s, 4pt=%s, 6pt=%s\n" % (self.consequences[0], self.consequences[1], self.consequences[2]))
    return desc.getvalue()

if __name__ == '__main__':
  player = Player("Bob", 4)
  print player.roll_fight(FateDiceHand())
  player.take_hit(10)
  print player
