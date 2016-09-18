class Object:
  def __init__(self, x, y, char, color):
    self.x = x
    self.y = y
    self.char = char
    self.color = color

  def move(self, dx, dy):
    if not self.dungeon_map.is_blocked(self.x + dx, self.y + dy):
      self.x += dx
      self.y += dy

  def draw(self, renderer):
    if self.dungeon_map.is_in_fov(self.x, self.y):
      renderer.draw_foreground(self.char, self.color, self.x, self.y)

  def clear(self, renderer):
    renderer.draw_foreground(' ', self.color, self.x, self.y)
