require "date"

sunday_1st = 0

1901.upto(2000) do |year|
  1.upto(12) do |month|
    sunday_1st += 1 if Date.new(year, month, 1).cwday == 7
  end
end


puts sunday_1st
