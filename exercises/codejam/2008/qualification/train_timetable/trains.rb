class HourMinutes
  include Comparable

  def initialize(h, m)
    @hour = h
    @minutes = m
  end

  def self.from_string(hm_string)
    h, m = hm_string.split(":")
    self.new(h.to_i, m.to_i)
  end

  def add(minutes)
    h = @hour
    m = @minutes + minutes
    if m >= 60
      h += 1
      m -= 60
    end
    HourMinutes.new(h, m)
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
    self.new(station, HourMinutes.from_string(d), HourMinutes.from_string(a))
  end
end

def trains_needed(turnaround, trips)
  trains = { :a => 0, :b => 0}
  available_times = { :a => [], :b => [] }

  trips.sort_by(&:departure).each do |trip|
    from, to = trip.from_station, trip.from_station == :a ? :b : :a

    available = available_times[from].find { |t| t <= trip.departure }
    if available
      available_times[from].delete_at(available_times[from].index(available) || available_times[from].length)
    else
      trains[from] += 1
    end

    available_times[to] << trip.arrival.add(turnaround)
  end

  return "#{trains[:a]} #{trains[:b]}"
end

gets.to_i.times do |case_id|
  t = gets.to_i
  na, nb = gets.split
  trips = []
  na.to_i.times { trips << Trip.from(:a, gets) }
  nb.to_i.times { trips << Trip.from(:b, gets) }

  puts "Case \##{case_id+1}: #{trains_needed(t, trips)}"
end
