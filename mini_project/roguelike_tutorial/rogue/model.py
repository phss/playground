import libtcod.libtcodpy as libtcod

class Object:
  def __init__(self, dungeon_map, x, y, char, color):
    self.x = x
    self.y = y
    self.char = char
    self.color = color
    self.dungeon_map = dungeon_map

  def move(self, dx, dy):
    if not self.dungeon_map.is_blocked(self.x + dx, self.y + dy):
      self.x += dx
      self.y += dy

  def draw(self, con):
    if self.dungeon_map.is_in_fov(self.x, self.y):
      libtcod.console_set_default_foreground(con, self.color)
      libtcod.console_put_char(con, self.x, self.y, self.char, libtcod.BKGND_NONE)

  def clear(self, con):
    libtcod.console_put_char(con, self.x, self.y, ' ', libtcod.BKGND_NONE)
