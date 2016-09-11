import libtcod.libtcodpy as libtcod
import rogue.map as map
import rogue.model as model
import rogue.render as render


SCREEN_WIDTH = 80
SCREEN_HEIGHT = 50
LIMIT_FPS = 20

MAP_WIDTH = 80
MAP_HEIGHT = 45

ROOM_MAX_SIZE = 10
ROOM_MIN_SIZE = 6
MAX_ROOMS = 30

# Rendering
def render_all():
  dungeon_map.draw(renderer)
  for object in objects:
    object.draw(renderer)

  renderer.render()

  for object in objects:
    object.clear(renderer)


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
renderer = render.Renderer(SCREEN_WIDTH, SCREEN_HEIGHT)

dungeon_map, start_position = map.make_dungeon(MAP_WIDTH, MAP_HEIGHT, ROOM_MIN_SIZE, ROOM_MAX_SIZE, MAX_ROOMS)

player = model.Object(dungeon_map, start_position[0], start_position[1], '@', libtcod.white)
dungeon_map.compute_fov(player.x, player.y)
objects = [player]

while not libtcod.console_is_window_closed():
  render_all()

  should_exit = handle_keys()
  if should_exit:
    break
