class Year

  def initialize(year)
    @year = year
  end

  def leap?
    is_vanilla_leap_year = evenly_divisible_by?(4)
    is_beginning_of_century = evenly_divisible_by?(100)

    return is_vanilla_leap_year && (!is_beginning_of_century || evenly_divisible_by?(400))
  end

 private

  def evenly_divisible_by?(divisor)
    (@year % divisor).zero?
  end

end
