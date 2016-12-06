input = File.readlines('files/problem2.txt').map(&:chomp).map(&:chars)

class Position
  attr_reader :x, :y

  def initialize(x, y)
    @x = x
    @y = y
  end

  def left
    Position.new(@x - 1, @y)
  end

  def right
    Position.new(@x + 1, @y)
  end

  def up
    Position.new(@x, @y - 1)
  end

  def down
    Position.new(@x, @y + 1)
  end
end

class Keypad
  def initialize(config)
    @config = config
  end

  def valid?(position)
    position.y >= 0 && position.y < @config.size && position.x >= 0 && position.x < @config[0].size
  end

  def button_at(position)
    @config[position.y][position.x]
  end
end

keypad = Keypad.new(
  [[1, 2, 3],
   [4, 5, 6],
   [7, 8, 9]])

pos = Position.new(1, 1)
keys = input.map do |instructions|
  instructions.each do |instruction|
    case instruction
    when 'U'
      new_pos = pos.up
    when 'D'
      new_pos = pos.down
    when 'L'
      new_pos = pos.left
    when 'R'
      new_pos = pos.right
    end
    pos = new_pos if keypad.valid?(new_pos)
  end
  keypad.button_at(pos)
end

puts keys.join
