class Year

  def initialize(year)
    @year = year
  end

  def leap?
    every_fourth_year = evenly_divisible_by?(4)
    not_beginning_of_century = !evenly_divisible_by?(100)
    unless_400th_year = evenly_divisible_by?(400)

    return every_fourth_year && (not_beginning_of_century || unless_400th_year)
  end

 private

  def evenly_divisible_by?(divisor)
    (@year % divisor).zero?
  end

end
