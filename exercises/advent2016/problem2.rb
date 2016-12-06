input = File.readlines('files/problem2.txt').map(&:chars)

button_mapping = {
  [0, 0] => 1,
  [1, 0] => 2,
  [2, 0] => 3,
  [0, 1] => 4,
  [1, 1] => 5,
  [2, 1] => 6,
  [0, 2] => 7,
  [1, 2] => 8,
  [2, 2] => 9
}

button_x = 0
button_y = 0
input.each do |instructions|
  instructions.each do |instruction|
    case instruction
    when 'U'
      button_y -= 1
    when 'D'
      button_y += 1
    when 'L'
      button_x -= 1
    when 'R'
      button_x += 1
    end
    button_x = [button_x, 0].max
    button_x = [button_x, 2].min
    button_y = [button_y, 0].max
    button_y = [button_y, 2].min
  end
  puts button_mapping[[button_x, button_y]]
end
