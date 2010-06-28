require "model.rb"
require "utils.rb"

def player_breakdown_by(criteria, filter = nil)
  players = ALL_PLAYERS
  if filter
    players = players.select do |player|
      player.instance_eval(filter)
    end
  end
  players.inject({}) do |breakdown, player|
    attribute = player.instance_eval(criteria)
    breakdown[attribute] ||= 0
    breakdown[attribute] += 1
    breakdown
  end
end