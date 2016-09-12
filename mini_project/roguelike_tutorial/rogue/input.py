import libtcod.libtcodpy as libtcod

class MovementInputHandler:
  def __init__(self, player, dungeon_map):
    self.player = player
    self.dungeon_map = dungeon_map

  def handle(self, key):
    if key.vk == libtcod.KEY_UP:
      self.player.move(0, -1)
      self.dungeon_map.compute_fov(self.player.x, self.player.y)
    elif key.vk == libtcod.KEY_DOWN:
      self.player.move(0, 1)
      self.dungeon_map.compute_fov(self.player.x, self.player.y)
    elif key.vk == libtcod.KEY_LEFT:
      self.player.move(-1, 0)
      self.dungeon_map.compute_fov(self.player.x, self.player.y)
    elif key.vk == libtcod.KEY_RIGHT:
      self.player.move(1, 0)
      self.dungeon_map.compute_fov(self.player.x, self.player.y)

class GeneralInputHandler:
  def handle(self, key):
    if key.vk == libtcod.KEY_ENTER and key.lalt:
      libtcod.console_set_fullscreen(not libtcod.console_is_fullscreen())
    elif key.vk == libtcod.KEY_ESCAPE:
      return True
    return False

def handle_keys(player, dungeon_map):
  key = libtcod.console_check_for_keypress(True) # True for turn-based
  MovementInputHandler(player, dungeon_map).handle(key)
  return GeneralInputHandler().handle(key)
