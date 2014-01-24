class Raindrops
  
  def convert(number)
    return "Pling" if number % 3 == 0
    return "Plang" if number % 5 == 0
    return "Plong" if number % 7 == 0
    "1"
  end

end
