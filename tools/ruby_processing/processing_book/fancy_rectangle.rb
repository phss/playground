# Fancy Rectangle

class FancyRectangle < Processing::App

  def setup
		background 225    
		@rect = Rectangle.new(width/4, height/4, width/2, height/2)
  end

	def draw
		@rect.draw
	end
  
	class Rectangle
		def initialize(x, y, width, height)
			@x, @y, @width, @height = x, y, width, height
		end

		def draw
			fill(random(255))
			rect @x, @y, @width, @height
		end
	end

end

FancyRectangle.new :title => "Fancy Rectangle", :width => 400, :height => 400
