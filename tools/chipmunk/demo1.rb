require 'rubygems'
require 'gosu'
require 'chipmunk'

SCREEN_WIDTH = 640
SCREEN_HEIGHT = 480
INFINITY = 1000

class GameWindow < Gosu::Window
  def initialize
    super(SCREEN_WIDTH, SCREEN_HEIGHT, false, 16)
    self.caption = "Demo1"
    
    static_body = CP::Body.new(INFINITY, INFINITY)
    
    space = CP::Space.new
    # Skipping space hash bit
    space.gravity = CP::Vec2.new(0, -100)
  end
  

  def update
  end

  def draw
  end

  def button_down(id)
    if id == Gosu::Button::KbEscape
      close
    end
  end
end

window = GameWindow.new
window.show