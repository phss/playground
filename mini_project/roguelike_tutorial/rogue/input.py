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
    return False

class GeneralInputHandler:
  def handle(self, key):
    if key.vk == libtcod.KEY_ENTER and key.lalt:
      libtcod.console_set_fullscreen(not libtcod.console_is_fullscreen())
    elif key.vk == libtcod.KEY_ESCAPE:
      return True
    return False

class Handler:
  def __init__(self, input_handlers):
    self.input_handlers = input_handlers

  def handle(self):
    key = libtcod.console_check_for_keypress(True) # True for turn-based
    for input_handler in self.input_handlers:
      result = input_handler.handle(key)
    return result

def handler_for(*input_handlers):
  return Handler(input_handlers)
