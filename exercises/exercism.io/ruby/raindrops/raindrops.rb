class Raindrops
  
  def convert(number)
    sounds = ""
    sounds += "Pling" if divisible?(number, 3)
    sounds += "Plang" if divisible?(number, 5)
    sounds += "Plong" if divisible?(number, 7)

    return number.to_s if sounds.empty?
    sounds
  end

 private

  def divisible?(number, divider)
    number % divider == 0
  end

end
