class Object:
  def __init__(self, x, y, char, color):
    self.x = x
    self.y = y
    self.char = char
    self.color = color

  def move(self, dx, dy):
    if not self.environment.dungeon_map.is_blocked(self.x + dx, self.y + dy):
      self.x += dx
      self.y += dy

  def draw(self, renderer):
    if self.environment.dungeon_map.is_in_fov(self.x, self.y):
      renderer.draw_foreground(self.char, self.color, self.x, self.y)

  def clear(self, renderer):
    renderer.draw_foreground(' ', self.color, self.x, self.y)


class Environment:
  def __init__(self, dungeon_map, objects):
    self.dungeon_map = dungeon_map
    self.objects = objects
