import libtcod.libtcodpy as libtcod

def handle_keys(player, dungeon_map):

  key = libtcod.console_check_for_keypress(True) # True for turn-based

  if key.vk == libtcod.KEY_UP:
    player.move(0, -1)
    dungeon_map.compute_fov(player.x, player.y)
  elif key.vk == libtcod.KEY_DOWN:
    player.move(0, 1)
    dungeon_map.compute_fov(player.x, player.y)
  elif key.vk == libtcod.KEY_LEFT:
    player.move(-1, 0)
    dungeon_map.compute_fov(player.x, player.y)
  elif key.vk == libtcod.KEY_RIGHT:
    player.move(1, 0)
    dungeon_map.compute_fov(player.x, player.y)


  if key.vk == libtcod.KEY_ENTER and key.lalt:
    libtcod.console_set_fullscreen(not libtcod.console_is_fullscreen())
  elif key.vk == libtcod.KEY_ESCAPE:
    return True
