require "./fighter"
require "./combat"
require "./dice"

fighter1 = Fighter.new("Bob", 10, 5, 5)
fighter2 = Fighter.new("Dave", 10, 5, 1)

combat = Combat.between(fighter1, fighter2)

combat.fight