class Trip

  attr_reader :from_station, :departure, :arrival

  def initialize(from_station, departure, arrival)
    @from_station, @departure, @arrival = from_station, departure, arrival
  end

  def self.from(station, time_string)
    d, a = time_string.split
    self.new(station, d, a)
  end

end

def trains_needed(turnaround, trips)
  trains_at_a, trains_at_b = 0, 0

  trips.sort_by { |t| t.departure }.each do |trip|
    puts trip.from_station
  end

  return "#{trains_at_a} #{trains_at_b}"
end

gets.to_i.times do |case_id|
  t = gets # Skip one
  na, nb = gets.split
  trips = []
  na.to_i.times { trips << Trip.from(:a, gets) }
  nb.to_i.times { trips << Trip.from(:b, gets) }

  puts "Case \##{case_id+1}: #{trains_needed(t, trips)}"
end
