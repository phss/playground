class SpaceAge
  attr_reader :seconds

  def initialize(seconds)
    @seconds = seconds   
    @earth_year_in_seconds = 365.25*24*60*60 
    @year_in_seconds = {
      "earth" => 1,
      "mercury" => 0.2408467,
      "venus" => 0.61519726,
      "mars" => 1.8808158,
      "jupiter" => 11.862615,
      "saturn" => 29.447498,
      "uranus" => 84.016846,
      "neptune" => 164.79132
    }
  end

  def method_missing(method, *args, &blk)
    planet = method.to_s[/on_(.*)/, 1]
    return years_on_planet(planet) if @year_in_seconds.has_key?(planet)
    super
  end

 private

  def years_on_planet(planet)
    planet_year_in_seconds = @year_in_seconds[planet] * @earth_year_in_seconds
    (@seconds / planet_year_in_seconds).round(2)
  end

end
