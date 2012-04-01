require "./fighter"
require "./combat"
require "./util/dice"
require "./scribe/combat_scribe"
require "./scribe/dodged_description"

fighter1 = Fighter.new("Bob", 10, 5, 5)
fighter2 = Fighter.new("Dave", 10, 5, 1)

Combat.between(fighter1, fighter2).fight
