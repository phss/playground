# Does It Work

class DoesItWork < Processing::App

  def setup
    
  end
  
  def draw
		triangle(10, 10, 600, 50, 300, 400)
		smooth
  end
  
end

DoesItWork.new :title => "Does It Work", :width => 640, :height => 480
