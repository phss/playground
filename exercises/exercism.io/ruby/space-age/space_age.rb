class SpaceAge
  attr_reader :seconds

  def initialize(seconds)
    @seconds = seconds   
    
    earth_year_in_seconds = 365.25*24*60*60 
    @year_in_seconds = {
      "earth" => earth_year_in_seconds,
      "mercury" => earth_year_in_seconds * 0.2408467,
      "venus" => earth_year_in_seconds * 0.61519726,
      "mars" => earth_year_in_seconds * 1.8808158,
      "jupiter" => earth_year_in_seconds * 11.862615,
      "saturn" => earth_year_in_seconds * 29.447498,
      "uranus" => earth_year_in_seconds * 84.016846,
      "neptune" => earth_year_in_seconds * 164.79132
    }
  end

  def method_missing(method, *args, &blk)
    planet = method.to_s[/on_(.*)/, 1]
    return years_on_planet(planet) if @year_in_seconds.has_key?(planet)
    super
  end

 private

  def years_on_planet(planet)
    (@seconds / @year_in_seconds[planet]).round(2)
  end

end
