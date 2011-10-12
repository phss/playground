require "date"

class GreenSlot < Struct.new(:start, :end); end

proj1 = [
  GreenSlot.new(0, 2),
  GreenSlot.new(5, 7),
  GreenSlot.new(9, 10)
]

proj2 = [
  GreenSlot.new(1, 2),
  GreenSlot.new(6, 9)
]


def print_matching_slots(projects)
  less_greens = projects.min { |a, b|  a.size <=> b.size }

  puts less_greens
end


class Canvas < Processing::App
  def setup
    frame_rate 30
    smooth
    fill 0
    @font = create_font('Helvetica', 40)
  end

  def draw
    background 255

    title
  end

  private

  def title
    text_font @font
    text "Chart", 40, 50
  end
end

Canvas.new :title => "Charting example", :width => 800, :height => 600
