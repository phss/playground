class Grains
  
  def square(number)
    return 1 if number == 1
    square(number - 1) * 2
  end

  def total
    (1..64).map { |i| square(i) }.reduce(&:+)
  end

end
