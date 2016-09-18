import libtcod.libtcodpy as libtcod
from collections import namedtuple

FOV_ALGO = 0
FOV_LIGHT_WALLS = True
TORCH_RADIUS = 10

def make_dungeon(map_width, map_height, room_min_size, room_max_size, max_rooms):
  dungeon_colors = MapColors(
    dark_wall = libtcod.Color(0, 0, 100),
    light_wall = libtcod.Color(130, 110, 50),
    dark_ground = libtcod.Color(50, 50, 150),
    light_ground = libtcod.Color(200, 180, 50))
  dungeon_map = Map(map_width, map_height, dungeon_colors)
  room_maker = RoomMaker(room_min_size, room_max_size, max_rooms)
  rooms = room_maker.build_rooms_on(dungeon_map)
  return (dungeon_map, rooms[0].center())

MapColors = namedtuple('MapColors', 'dark_wall light_wall dark_ground light_ground')

class Map:
  def __init__(self, width, height, colors):
    self.width = width
    self.height = height
    self.colors = colors
    self.tiles = [[ Tile(True) for y in range(height)] for x in range(width)]
    self.fov = libtcod.map_new(width, height)
    for y in range(self.height):
      for x in range(self.width):
        self._update_fov(x, y)

  def draw(self, renderer):
    for y in range(self.height):
      for x in range(self.width):
        visible = self.is_in_fov(x, y)
        wall = self.is_wall(x, y)
        if visible:
          color = self.colors.light_wall if wall else self.colors.light_ground
          self.set_explored(x, y)
          renderer.draw_background(color, x, y)
        elif self.is_explored(x, y):
          color = self.colors.dark_wall if wall else self.colors.dark_ground
          renderer.draw_background(color, x, y)

  def set_wall_on(self, x, y):
    self.tiles[x][y].set_wall()
    self._update_fov(x, y)

  def is_wall(self, x, y):
    return self.tiles[x][y].block_sight

  def is_blocked(self, x, y):
    return self.tiles[x][y].blocked

  def set_explored(self, x, y):
    self.tiles[x][y].explored = True

  def is_explored(self, x, y):
    return self.tiles[x][y].explored

  def is_in_fov(self, x, y):
    return libtcod.map_is_in_fov(self.fov, x, y)

  def compute_fov(self, x, y):
    libtcod.map_compute_fov(self.fov, x, y, TORCH_RADIUS, FOV_LIGHT_WALLS, FOV_ALGO)

  def _update_fov(self, x, y):
    libtcod.map_set_properties(self.fov, x, y,
                                not self.is_wall(x, y),
                                not self.is_blocked(x, y))


class Tile:
  def __init__(self, blocked, block_sight = None):
    self.blocked = blocked
    if block_sight is None: block_sight = blocked
    self.block_sight = block_sight
    self.explored = False

  def set_wall(self):
    self.blocked = False
    self.block_sight = False


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


# Map maker
class RoomMaker:
  def __init__(self, room_min_size, room_max_size, max_rooms):
    self.room_min_size = room_min_size
    self.room_max_size = room_max_size
    self.max_rooms = max_rooms

  def build_rooms_on(self, dungeon_map):
    rooms = []
    objects = []

    for r in range(self.max_rooms):
      w = libtcod.random_get_int(0, self.room_min_size, self.room_max_size)
      h = libtcod.random_get_int(0, self.room_min_size, self.room_max_size)
      x = libtcod.random_get_int(0, 0, dungeon_map.width - w - 1)
      y = libtcod.random_get_int(0, 0, dungeon_map.height - h - 1)

      new_room = Rect(x, y, w, h)
      failed = False
      for other_room in rooms:
        if new_room.intersect(other_room):
          failed = True
          break

      if not failed:
        self._create_room(dungeon_map, new_room)

        (new_x, new_y) = new_room.center()

        if len(rooms) > 0:
          (prev_x, prev_y) = rooms[len(rooms)-1].center()

          if libtcod.random_get_int(0, 0, 1) == 1:
            self._create_h_tunnel(dungeon_map, prev_x, new_x, prev_y)
            self._create_v_tunnel(dungeon_map, prev_y, new_y, new_x)
          else:
            self._create_v_tunnel(dungeon_map, prev_y, new_y, prev_x)
            self._create_h_tunnel(dungeon_map, prev_x, new_x, new_y)

        rooms.append(new_room)
    return rooms

  def _create_room(self, dungeon_map, room):
    for x in range(room.x1 + 1, room.x2):
      for y in range(room.y1 + 1, room.y2):
        dungeon_map.set_wall_on(x, y)

  def _create_h_tunnel(self, dungeon_map, x1, x2, y):
    for x in range(min(x1, x2), max(x1, x2) + 1):
      dungeon_map.set_wall_on(x, y)

  def _create_v_tunnel(self, dungeon_map, y1, y2, x):
    for y in range(min(y1, y2), max(y1, y2) + 1):
      dungeon_map.set_wall_on(x, y)

