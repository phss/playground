require 'rubygems'
require 'gosu'
require 'chipmunk'

SCREEN_WIDTH = 640
SCREEN_HEIGHT = 480

class GameObject
  
  attr_reader :body, :shape
  
  def initialize(game_window, x, y, width, height, collision, color=Gosu::white)
    @game_window = game_window
    @width = width
    @height = height
    @color = color
    
    @body = CP::Body.new(10.0, 150.0)
    @body.p = CP::Vec2.new(x, y)
    
    shape_array = [CP::Vec2.new(0, 0), CP::Vec2.new(0, @width), CP::Vec2.new(@width, @height), CP::Vec2.new(0, @height)]
    @shape = CP::Shape::Poly.new(@body, shape_array, CP::Vec2.new(0,0))
    @shape.collision_type = collision
  end
  
  def draw
    @game_window.draw_quad(
      @body.p.x, @body.p.y, @color,
      @body.p.x + @width, @body.p.y, @color,
      @body.p.x, @body.p.y + @height, @color,
      @body.p.x + @width, @body.p.y + @height, @color)
  end
  
end

class GameWindow < Gosu::Window
  def initialize
    super(SCREEN_WIDTH, SCREEN_HEIGHT, false, 16)
    self.caption = "Arkanoid Chipmunk Demo"
    
    @space = CP::Space.new
    
    @left = GameObject.new(self, 0, 0, 10, SCREEN_HEIGHT, :wall)
    @top = GameObject.new(self, 0, 0, SCREEN_WIDTH, 10, :wall)
    @right = GameObject.new(self, SCREEN_WIDTH-10, 0, 10, SCREEN_HEIGHT, :wall)
    @bottom = GameObject.new(self, 0, SCREEN_HEIGHT-10, SCREEN_WIDTH, 10, :wall)
    add_to_space(@left)
    add_to_space(@top)
    add_to_space(@right)
    add_to_space(@bottom)
    
    @ball = GameObject.new(self, SCREEN_WIDTH/2, SCREEN_HEIGHT/2, 10, 10, :ball, Gosu::red)
    add_to_space(@ball)
    @ball.shape.body.apply_impulse(CP::Vec2.new(2000.0, 0.0), CP::Vec2.new(0.0, 0.0))
    
    @space.add_collision_func(:ball, :wall) do |ball, wall|
      puts "colide"
    end
    
    @space.add_collision_func(:wall, :wall)
  end
  
  def add_to_space(obj)
    @space.add_body(obj.body)
    @space.add_shape(obj.shape)
  end

  def update
    # @ball.shape.body.reset_forces
    
    @space.step(1.0/60.0)
  end

  def draw
    @left.draw
    @top.draw
    @right.draw
    @bottom.draw
    @ball.draw
  end

  def button_down(id)
    if id == Gosu::Button::KbEscape
      close
    end
  end
end

window = GameWindow.new
window.show