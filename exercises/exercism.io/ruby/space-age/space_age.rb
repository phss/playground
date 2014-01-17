class SpaceAge
  attr_reader :seconds

  def initialize(seconds)
    @seconds = seconds   
  end

  def on_earth
    year_in_days = 365.25
    year_in_seconds = year_in_days*24*60*60
    (@seconds / year_in_seconds).round(2)
  end

  def on_mercury
    year_in_days = 365.25
    year_in_seconds = year_in_days*24*60*60
    year_in_seconds_merc = year_in_seconds * 0.2408467
    (@seconds / year_in_seconds_merc).round(2)
  end

end
