import libtcod.libtcodpy as libtcod
import rogue.map as map

SCREEN_WIDTH = 80
SCREEN_HEIGHT = 50
LIMIT_FPS = 20

MAP_WIDTH = 80
MAP_HEIGHT = 45

ROOM_MAX_SIZE = 10
ROOM_MIN_SIZE = 6
MAX_ROOMS = 30

FOV_ALGO = 0
FOV_LIGHT_WALLS = True
TORCH_RADIUS = 10

color_dark_wall = libtcod.Color(0, 0, 100)
color_light_wall = libtcod.Color(130, 110, 50)
color_dark_ground = libtcod.Color(50, 50, 150)
color_light_ground = libtcod.Color(200, 180, 50)

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
    if libtcod.map_is_in_fov(fov_map, self.x, self.y):
      libtcod.console_set_default_foreground(con, self.color)
      libtcod.console_put_char(con, self.x, self.y, self.char, libtcod.BKGND_NONE)

  def clear(self):
    libtcod.console_put_char(con, self.x, self.y, ' ', libtcod.BKGND_NONE)

# Rendering
def render_all():
  global fov_recompute

  if fov_recompute:
    fov_recompute = False
    libtcod.map_compute_fov(fov_map, player.x, player.y, TORCH_RADIUS, FOV_LIGHT_WALLS, FOV_ALGO)

  for object in objects:
    object.draw()

  for y in range(MAP_HEIGHT):
    for x in range(MAP_WIDTH):
      visible = libtcod.map_is_in_fov(fov_map, x, y)
      wall = dungeon[x][y].block_sight
      if visible:
        color = color_light_wall if wall else color_light_ground
        dungeon[x][y].explored = True
        libtcod.console_set_char_background(con, x, y, color, libtcod.BKGND_SET )
      elif dungeon[x][y].explored:
        color = color_dark_wall if wall else color_dark_ground
        libtcod.console_set_char_background(con, x, y, color, libtcod.BKGND_SET )

  libtcod.console_blit(con, 0, 0, SCREEN_WIDTH, SCREEN_HEIGHT, 0, 0, 0)


# Input
def handle_keys():
  global fov_recompute
  global playerx, playery

  if libtcod.console_is_key_pressed(libtcod.KEY_UP):
    player.move(0, -1)
    fov_recompute = True
  elif libtcod.console_is_key_pressed(libtcod.KEY_DOWN):
    player.move(0, 1)
    fov_recompute = True
  elif libtcod.console_is_key_pressed(libtcod.KEY_LEFT):
    player.move(-1, 0)
    fov_recompute = True
  elif libtcod.console_is_key_pressed(libtcod.KEY_RIGHT):
    player.move(1, 0)
    fov_recompute = True

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

dungeon, start_position = map.make_dungeon(MAP_WIDTH, MAP_HEIGHT, ROOM_MIN_SIZE, ROOM_MAX_SIZE, MAX_ROOMS)

player = Object(start_position[0], start_position[1], '@', libtcod.white)
npc = Object(SCREEN_WIDTH/2 - 5, SCREEN_HEIGHT/2, '@', libtcod.yellow)
objects = [npc, player]

fov_recompute = True
fov_map = libtcod.map_new(MAP_WIDTH, MAP_HEIGHT)
for y in range(MAP_HEIGHT):
  for x in range(MAP_WIDTH):
    libtcod.map_set_properties(fov_map, x, y, not dungeon[x][y].block_sight, not dungeon[x][y].blocked)

while not libtcod.console_is_window_closed():
  render_all()

  libtcod.console_flush()

  for object in objects:
    object.clear()

  should_exit = handle_keys()
  if should_exit:
    break
