import libtcodpy as libtcod

SCREEN_WIDTH = 80
SCREEN_HEIGHT = 50
LIMIT_FPS = 20

playerx = SCREEN_WIDTH / 2
playery = SCREEN_HEIGHT / 2

# Input
def handle_keys():
  global playerx, playery

  if libtcod.console_is_key_pressed(libtcod.KEY_UP):
    playery -= 1
  elif libtcod.console_is_key_pressed(libtcod.KEY_DOWN):
    playery += 1
  elif libtcod.console_is_key_pressed(libtcod.KEY_LEFT):
    playerx -= 1
  elif libtcod.console_is_key_pressed(libtcod.KEY_RIGHT):
    playerx += 1

  key = libtcod.console_check_for_keypress(True) # True for turn-based
  if key.vk == libtcod.KEY_ENTER and key.lalt:
    libtcod.console_set_fullscreen(not libtcod.console_is_fullscreen())
  elif key.vk == libtcod.KEY_ESCAPE:
    return True

# Setup and main loop
libtcod.console_set_custom_font('arial10x10.png', libtcod.FONT_TYPE_GREYSCALE | libtcod.FONT_LAYOUT_TCOD)
libtcod.console_init_root(SCREEN_WIDTH, SCREEN_HEIGHT, 'python/libtcod tutorial', False)
libtcod.sys_set_fps(LIMIT_FPS)

while not libtcod.console_is_window_closed():
  # Display
  libtcod.console_set_default_foreground(0, libtcod.white)
  libtcod.console_put_char(0, playerx, playery, '@', libtcod.BKGND_NONE)
  libtcod.console_flush()

  libtcod.console_put_char(0, playerx, playery, ' ', libtcod.BKGND_NONE)

  should_exit = handle_keys()
  if should_exit:
    break
