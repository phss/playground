class GameWindow < Slick::BasicGame
  
  def initialize
    super("Test")
  end
  
  def init(container)
    @image = Slick::Image.new("data/tetris-amarelo.png")
  end
  
  def update(container, delta)
  end
  
  def render(container, graphics)
    @image.draw(20, 20)
  end

end