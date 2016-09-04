import libtcodpy as libtcod

SCREEN_WIDTH = 80
SCREEN_HEIGHT = 50
LIMIT_FPS = 20

MAP_WIDTH = 80
MAP_HEIGHT = 45

color_dark_wall = libtcod.Color(0, 0, 100)
color_dark_ground = libtcod.Color(50, 50, 150)

# Model
class Object:
  def __init__(self, x, y, char, color):
    self.x = x
    self.y = y
    self.char = char
    self.color = color

  def move(self, dx, dy):
    if not dungeon[self.x + dx][self.y + dy].blocked:
      self.x += dx
      self.y += dy

  def draw(self):
    libtcod.console_set_default_foreground(con, self.color)
    libtcod.console_put_char(con, self.x, self.y, self.char, libtcod.BKGND_NONE)

  def clear(self):
    libtcod.console_put_char(con, self.x, self.y, ' ', libtcod.BKGND_NONE)

class Tile:
  def __init__(self, blocked, block_sight = None):
    self.blocked = blocked
    if block_sight is None: block_sight = blocked
    self.block_sight = block_sight

def make_dungeon():
  global dungeon

  dungeon = [[ Tile(False) for y in range(MAP_HEIGHT)] for x in range(MAP_WIDTH)]
  dungeon[30][22].blocked = True
  dungeon[30][22].block_sight = True
  dungeon[50][22].blocked = True
  dungeon[50][22].block_sight = True


# Rendering
def render_all():
  for object in objects:
    object.draw()

  for y in range(MAP_HEIGHT):
    for x in range(MAP_WIDTH):
      wall = dungeon[x][y].block_sight
      if wall:
          libtcod.console_set_char_background(con, x, y, color_dark_wall, libtcod.BKGND_SET )
      else:
          libtcod.console_set_char_background(con, x, y, color_dark_ground, libtcod.BKGND_SET )

  libtcod.console_blit(con, 0, 0, SCREEN_WIDTH, SCREEN_HEIGHT, 0, 0, 0)


# Input
def handle_keys():
  global playerx, playery

  if libtcod.console_is_key_pressed(libtcod.KEY_UP):
    player.move(0, -1)
  elif libtcod.console_is_key_pressed(libtcod.KEY_DOWN):
    player.move(0, 1)
  elif libtcod.console_is_key_pressed(libtcod.KEY_LEFT):
    player.move(-1, 0)
  elif libtcod.console_is_key_pressed(libtcod.KEY_RIGHT):
    player.move(1, 0)

  key = libtcod.console_check_for_keypress(True) # True for turn-based
  if key.vk == libtcod.KEY_ENTER and key.lalt:
    libtcod.console_set_fullscreen(not libtcod.console_is_fullscreen())
  elif key.vk == libtcod.KEY_ESCAPE:
    return True

# Setup and main loop
libtcod.console_set_custom_font('arial10x10.png', libtcod.FONT_TYPE_GREYSCALE | libtcod.FONT_LAYOUT_TCOD)
libtcod.console_init_root(SCREEN_WIDTH, SCREEN_HEIGHT, 'python/libtcod tutorial', False)
libtcod.sys_set_fps(LIMIT_FPS)
con = libtcod.console_new(SCREEN_WIDTH, SCREEN_HEIGHT)

player = Object(SCREEN_WIDTH/2, SCREEN_HEIGHT/2, '@', libtcod.white)
npc = Object(SCREEN_WIDTH/2 - 5, SCREEN_HEIGHT/2, '@', libtcod.yellow)
objects = [npc, player]
make_dungeon()

while not libtcod.console_is_window_closed():
  render_all()

  libtcod.console_flush()

  for object in objects:
    object.clear()

  should_exit = handle_keys()
  if should_exit:
    break
