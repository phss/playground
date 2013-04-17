def trains_needed(turnaround, ab_trips, ba_trips)
  "2 0"
end

gets.to_i.times do |case_id|
  t = gets # Skip one
  na, nb = gets.split(" ")   
  ab_trips = []
  ba_trips = []
  na.to_i.times { ab_trips << gets }
  nb.to_i.times { ba_trips << gets }

  puts "Case \##{case_id+1}: #{trains_needed(t, ab_trips, ba_trips)}"
end
