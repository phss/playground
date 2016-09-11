import libtcod.libtcodpy as libtcod

class Renderer:
  def __init__(self, width, height):
    self.con = libtcod.console_new(width, height)
    self.width = width
    self.height = height

  def draw_foreground(self, char, color, x, y):
    libtcod.console_set_default_foreground(self.con, color)
    libtcod.console_put_char(self.con, x, y, char, libtcod.BKGND_NONE)

  def draw_background(self, color, x, y):
    libtcod.console_set_char_background(self.con, x, y, color, libtcod.BKGND_SET)

  def render(self):
    libtcod.console_blit(self.con, 0, 0, self.width, self.height, 0, 0, 0)
    libtcod.console_flush()

