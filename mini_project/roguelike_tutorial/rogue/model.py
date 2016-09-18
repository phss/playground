class Object:
  def __init__(self, x, y, char, color, blocks = False):
    self.x = x
    self.y = y
    self.char = char
    self.color = color
    self.blocks = blocks

  def move(self, dx, dy):
    if not self.environment.is_blocked(self.x + dx, self.y + dy):
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

  def is_blocked(self, x, y):
    if self.dungeon_map.is_blocked(x, y):
      return True

    for object in self.objects:
      if object.blocks and object.x == x and object.y == y:
        return True

    return False
