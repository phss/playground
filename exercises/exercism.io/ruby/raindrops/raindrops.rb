class Raindrops
  
  def convert(number)
    return "Pling" if divisible?(number, 3)
    return "Plang" if divisible?(number, 5)
    return "Plong" if divisible?(number, 7)
    "1"
  end

 private

  def divisible?(number, divider)
    number % divider == 0
  end

end
