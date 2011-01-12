require "date"

class GreenSlot < Struct.new(:start, :end); end

proj1 = [
  GreenSlot.new(DateTime.new(2010, 1, 30, 10, 20), DateTime.new(2010, 1, 31, 15, 50)),
  GreenSlot.new(DateTime.new(2010, 1, 31, 18, 19), DateTime.new(2010, 1, 31, 22, 31)),
  GreenSlot.new(DateTime.new(2010, 2, 2, 3, 0), DateTime.new(2010, 2, 4, 15, 50))
]

proj2 = [
  GreenSlot.new(DateTime.new(2010, 1, 30, 23, 20), DateTime.new(2010, 1, 31, 19, 50)),
  GreenSlot.new(DateTime.new(2010, 1, 31, 23, 20), DateTime.new(2010, 2, 1, 19, 50))
]


def print_matching_slots(projects)
  less_greens = projects.min { |a, b|  a.size <=> b.size }

  puts less_greens
end

print_matching_slots([proj1, proj2])

