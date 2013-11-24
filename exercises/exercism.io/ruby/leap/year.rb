class Year

  def initialize(year)
    @year = year
  end

  def leap?
    vanilla_leap? && (!beginning_of_century? || evenly_divisible_by?(400))
  end

 private

  def vanilla_leap?
    evenly_divisible_by?(4)
  end

  def beginning_of_century?
    evenly_divisible_by?(100)
  end

  def evenly_divisible_by?(divisor)
    (@year % divisor).zero?
  end

end
