class Raindrops
  
  def convert(number)
    sounds = ""
    sounds += "Pling" if divisible?(number, 3)
    sounds += "Plang" if divisible?(number, 5)
    sounds += "Plong" if divisible?(number, 7)

    return sounds.empty? ? number.to_s : sounds
  end

 private

  def divisible?(number, divider)
    number % divider == 0
  end

end
