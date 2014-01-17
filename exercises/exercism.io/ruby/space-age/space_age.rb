class SpaceAge
  attr_reader :seconds

  def initialize(seconds)
    @seconds = seconds   
    
    earth_year_in_seconds = 365.25*24*60*60 
    @year_in_seconds = {
      :earth => earth_year_in_seconds,
      :mercury => earth_year_in_seconds * 0.2408467
    }
  end

  def on_earth
    years_on_planet(:earth)
  end

  def on_mercury
    years_on_planet(:mercury)
  end

 private

  def years_on_planet(planet)
    (@seconds / @year_in_seconds[planet]).round(2)
  end

end
