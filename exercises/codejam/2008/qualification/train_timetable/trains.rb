def trains_needed(ab_trips, ba_trips)
  "2 0"
end

gets.to_i.times do |case_id|
  gets # Skip one
  na, nb = gets.split(" ")   
  ab_trips = []
  ba_trips = []
  na.to_i.times { ab_trips << gets }
  nb.to_i.times { ba_trips << gets }

  puts "Case \##{case_id+1}: #{trains_needed(ab_trips, ba_trips)}"
end
