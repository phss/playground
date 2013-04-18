class HourMinutes
  include Comparable

  def initialize(hm_string)
    h, m = hm_string.split(":")
    @hour = h.to_i
    @minutes = m.to_i
  end

  def add(minutes)
    @minutes += minutes
    if @minutes >= 60
      @hour += 1
      @minutes -= 60
    end
  end

  def stamp
    @hour * 100 + @minutes
  end

  def <=>(other)
    stamp <=> other.stamp 
  end
end

class Trip

  attr_reader :from_station, :departure, :arrival

  def initialize(from_station, departure, arrival)
    @from_station, @departure, @arrival = from_station, departure, arrival
  end

  def self.from(station, time_string)
    d, a = time_string.split
    self.new(station, HourMinutes.new(d), HourMinutes.new(a))
  end
end

def trains_needed(turnaround, trips)
  trains_at_a, trains_at_b = 0, 0

  trips.sort_by(&:departure).each do |trip|
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
