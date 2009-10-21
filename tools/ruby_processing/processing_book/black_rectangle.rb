# Black Rectangle

class BlackRectangle < Processing::App

  def draw
 		background 255 
		no_stroke
		fill 0
		rect(width/4, height/4, width/2, height/2)
  end
  
end

BlackRectangle.new :title => "Black Rectangle", :width => 400, :height => 400
