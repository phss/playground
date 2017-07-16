#!/usr/bin/env python

from StringIO import StringIO

class Player(object):
  def __init__(self, name, fight_attr):
    self.name = name
    self.fight_attr = fight_attr
    self.stress = [None, None]
    self.consequences = [None, None, None]

  def __str__(self):
    desc = StringIO()
    desc.write("Player: %s (%d)\n" % (self.name, self.fight_attr))
    desc.write("Stress: 1pt=%s, 2pt=%s\n" % (self.stress[0], self.stress[1]))
    desc.write("Consequences: 2pt=%s, 4pt=%s, 6pt=%s\n" % (self.consequences[0], self.consequences[1], self.consequences[2]))
    return desc.getvalue()

if __name__ == '__main__':
  player = Player("Bob", 1)
  print player
