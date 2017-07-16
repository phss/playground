#!/usr/bin/env python

from dice import FateDiceHand
from player import Player

def attack(dice, attacker, defender):
  attack_roll = attacker.roll_fight(dice)
  defence_roll = defender.roll_fight(dice)
  shift = attack_roll - defence_roll
  if shift > 0:
    print "%s hits %s for %d points" % (attacker.name, defender.name, shift)
    defender.take_hit(attack_roll - defence_roll)
  else:
    print "%s misses %s" % (attacker.name, defender.name)

def combat(p1, p2):
  dice = FateDiceHand()
  round = 1
  while p1.alive and p2.alive:
    print "=== Round %d ===" % round
    round += 1

    attack(dice, p1, p2)
    attack(dice, p2, p1)

    print
    print p1
    print
    print p2


if __name__ == '__main__':
  p1 = Player('Alice', 1)
  p2 = Player('Bob', 1)
  combat(p1, p2)
