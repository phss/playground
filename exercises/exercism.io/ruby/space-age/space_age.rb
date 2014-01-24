class SpaceAge
  EARTH_YEAR_IN_SECONDS = 365.25*24*60*60 
  PERIOD_IN_EARTH_YEARS = {
    "earth" => 1,
    "mercury" => 0.2408467,
    "venus" => 0.61519726,
    "mars" => 1.8808158,
    "jupiter" => 11.862615,
    "saturn" => 29.447498,
    "uranus" => 84.016846,
    "neptune" => 164.79132
  }

  PERIOD_IN_EARTH_YEARS.each do |planet, earth_years|
    define_method("on_#{planet}") do 
      planet_year_in_seconds = earth_years * EARTH_YEAR_IN_SECONDS
      (@seconds / planet_year_in_seconds).round(2)
    end
  end

  attr_reader :seconds

  def initialize(seconds)
    @seconds = seconds   
  end

end
