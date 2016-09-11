import libtcod.libtcodpy as libtcod
import rogue.map as map
import rogue.model as model


SCREEN_WIDTH = 80
SCREEN_HEIGHT = 50
LIMIT_FPS = 20

MAP_WIDTH = 80
MAP_HEIGHT = 45

ROOM_MAX_SIZE = 10
ROOM_MIN_SIZE = 6
MAX_ROOMS = 30

# Rendering
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

  def blit(self):
    libtcod.console_blit(self.con, 0, 0, self.width, self.height, 0, 0, 0)

def render_all():
  dungeon_map.draw(renderer)
  for object in objects:
    object.draw(renderer)
  renderer.blit()


# Input
def handle_keys():
  if libtcod.console_is_key_pressed(libtcod.KEY_UP):
    player.move(0, -1)
    dungeon_map.compute_fov(player.x, player.y)
  elif libtcod.console_is_key_pressed(libtcod.KEY_DOWN):
    player.move(0, 1)
    dungeon_map.compute_fov(player.x, player.y)
  elif libtcod.console_is_key_pressed(libtcod.KEY_LEFT):
    player.move(-1, 0)
    dungeon_map.compute_fov(player.x, player.y)
  elif libtcod.console_is_key_pressed(libtcod.KEY_RIGHT):
    player.move(1, 0)
    dungeon_map.compute_fov(player.x, player.y)

  key = libtcod.console_check_for_keypress(True) # True for turn-based
  if key.vk == libtcod.KEY_ENTER and key.lalt:
    libtcod.console_set_fullscreen(not libtcod.console_is_fullscreen())
  elif key.vk == libtcod.KEY_ESCAPE:
    return True

# Setup and main loop
libtcod.console_set_custom_font('arial10x10.png', libtcod.FONT_TYPE_GREYSCALE | libtcod.FONT_LAYOUT_TCOD)
libtcod.console_init_root(SCREEN_WIDTH, SCREEN_HEIGHT, 'python/libtcod tutorial', False)
libtcod.sys_set_fps(LIMIT_FPS)
renderer = Renderer(SCREEN_WIDTH, SCREEN_HEIGHT)

dungeon_map, start_position = map.make_dungeon(MAP_WIDTH, MAP_HEIGHT, ROOM_MIN_SIZE, ROOM_MAX_SIZE, MAX_ROOMS)

player = model.Object(dungeon_map, start_position[0], start_position[1], '@', libtcod.white)
dungeon_map.compute_fov(player.x, player.y)
objects = [player]

while not libtcod.console_is_window_closed():
  render_all()

  libtcod.console_flush()

  for object in objects:
    object.clear(renderer)

  should_exit = handle_keys()
  if should_exit:
    break
