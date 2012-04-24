require "./fighter"
require "./combat"
require "./util/dice"
require "./scribe/combat_scribe"
require "./scribe/description_scribe"
require "./scribe/production_grammar"
require "./scribe/descriptions_grammar"

fighter1 = Fighter.new("Bob", 10, 5, 5)
fighter2 = Fighter.new("Dave", 10, 5, 1)

Combat.between(fighter1, fighter2).fight
