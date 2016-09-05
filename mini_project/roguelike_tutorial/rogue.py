import libtcodpy as libtcod

SCREEN_WIDTH = 80
SCREEN_HEIGHT = 50
LIMIT_FPS = 20

MAP_WIDTH = 80
MAP_HEIGHT = 45

ROOM_MAX_SIZE = 10
ROOM_MIN_SIZE = 6
MAX_ROOMS = 30

color_dark_wall = libtcod.Color(0, 0, 100)
color_dark_ground = libtcod.Color(50, 50, 150)

# Model
class Rect:
  def __init__(self, x, y, w, h):
    self.x1 = x
    self.y1 = y
    self.x2 = x + w
    self.y2 = y + h

  def center(self):
    center_x = (self.x1 + self.x2) / 2
    center_y = (self.y1 + self.y2) / 2
    return (center_x, center_y)

  def intersect(self, other):
    return (self.x1 <= other.x2 and self.x2 >= other.x1 and
            self.y1 <= other.y2 and self.y2 >= other.y1)

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


# Map maker
def create_room(room):
  global dungeon
  for x in range(room.x1 + 1, room.x2):
    for y in range(room.y1 + 1, room.y2):
      dungeon[x][y].blocked = False
      dungeon[x][y].block_sight = False

def create_h_tunnel(x1, x2, y):
  global dungeon
  for x in range(min(x1, x2), max(x1, x2) + 1):
    dungeon[x][y].blocked = False
    dungeon[x][y].block_sight = False

def create_v_tunnel(y1, y2, x):
  global dungeon
  #vertical tunnel
  for y in range(min(y1, y2), max(y1, y2) + 1):
    dungeon[x][y].blocked = False
    dungeon[x][y].block_sight = False

def make_dungeon():
  global dungeon
  dungeon = [[ Tile(True) for y in range(MAP_HEIGHT)] for x in range(MAP_WIDTH)]

  rooms = []

  for r in range(MAX_ROOMS):
    w = libtcod.random_get_int(0, ROOM_MIN_SIZE, ROOM_MAX_SIZE)
    h = libtcod.random_get_int(0, ROOM_MIN_SIZE, ROOM_MAX_SIZE)
    x = libtcod.random_get_int(0, 0, MAP_WIDTH - w - 1)
    y = libtcod.random_get_int(0, 0, MAP_HEIGHT - h - 1)

    new_room = Rect(x, y, w, h)
    failed = False
    for other_room in rooms:
      if new_room.intersect(other_room):
        failed = True
        break

    if not failed:
      create_room(new_room)

      (new_x, new_y) = new_room.center()

      # Debug
      room_no = Object(new_x, new_y, chr(65+len(rooms)), libtcod.white)
      objects.insert(0, room_no)

      if len(rooms) == 0:
        player.x = new_x
        player.y = new_y
      else:
        (prev_x, prev_y) = rooms[len(rooms)-1].center()

        if libtcod.random_get_int(0, 0, 1) == 1:
          create_h_tunnel(prev_x, new_x, prev_y)
          create_v_tunnel(prev_y, new_y, new_x)
        else:
          create_v_tunnel(prev_y, new_y, prev_x)
          create_h_tunnel(prev_x, new_x, new_y)

      rooms.append(new_room)

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
