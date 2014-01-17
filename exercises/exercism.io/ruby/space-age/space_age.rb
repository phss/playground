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

end
