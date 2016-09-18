import libtcod.libtcodpy as libtcod
import rogue.map as map
import rogue.model as model
import rogue.render as render
import rogue.input as input


SCREEN_WIDTH = 80
SCREEN_HEIGHT = 50
LIMIT_FPS = 40

MAP_WIDTH = 80
MAP_HEIGHT = 45

ROOM_MAX_SIZE = 10
ROOM_MIN_SIZE = 6
MAX_ROOMS = 30
MAX_ROOM_MONSTERS = 3


class Game:
  def __init__(self):
    self.dungeon_map, monsters, start_position = map.make_dungeon(MAP_WIDTH, MAP_HEIGHT, ROOM_MIN_SIZE, ROOM_MAX_SIZE, MAX_ROOMS, MAX_ROOM_MONSTERS)
    player = model.Object(start_position[0], start_position[1], '@', libtcod.white)
    self.dungeon_map.compute_fov(player.x, player.y)
    self.objects = [player] + monsters
    for object in self.objects:
      object.dungeon_map = self.dungeon_map
    self.renderer = render.Renderer(SCREEN_WIDTH, SCREEN_HEIGHT)
    self.input_handler = input.handler_for(
      input.MovementInputHandler(player, self.dungeon_map),
      input.GeneralInputHandler())

  def main_loop(self):
    while not libtcod.console_is_window_closed():
      self.render_all()

      should_exit = self.handle_keys()
      if should_exit:
        break

  def render_all(self):
    self.dungeon_map.draw(self.renderer)
    for object in self.objects:
      object.draw(self.renderer)

    self.renderer.render()

    for object in self.objects:
      object.clear(self.renderer)

  def handle_keys(self):
    return self.input_handler.handle()


if __name__ == '__main__':
  libtcod.console_set_custom_font('arial10x10.png', libtcod.FONT_TYPE_GREYSCALE | libtcod.FONT_LAYOUT_TCOD)
  libtcod.console_init_root(SCREEN_WIDTH, SCREEN_HEIGHT, 'python/libtcod tutorial', False)
  libtcod.sys_set_fps(LIMIT_FPS)
  Game().main_loop()
