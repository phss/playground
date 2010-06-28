require "queries.rb"

print_breakdown(player_breakdown_by("name", "club.country == 'BRA' && country.name != 'brazil'"))
