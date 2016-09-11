import libtcod.libtcodpy as libtcod

FOV_ALGO = 0
FOV_LIGHT_WALLS = True
TORCH_RADIUS = 10

def make_dungeon(map_width, map_height, room_min_size, room_max_size, max_rooms):
  dungeon = Map(map_width, map_height)
  room_maker = RoomMaker(room_min_size, room_max_size, max_rooms)
  start_pos = room_maker.build_rooms_on(dungeon)
  dungeon._init_fov()

  return (dungeon, start_pos)


class Map:
  def __init__(self, width, height):
    self.width = width
    self.height = height
    self.tiles = [[ Tile(True) for y in range(height)] for x in range(width)]
    self.fov = libtcod.map_new(width, height)

  def set_wall_on(self, x, y):
    self.tiles[x][y].set_wall()

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

  def _init_fov(self):
    for y in range(self.height):
      for x in range(self.width):
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

  def build_rooms_on(self, dungeon):
    rooms = []

    for r in range(self.max_rooms):
      w = libtcod.random_get_int(0, self.room_min_size, self.room_max_size)
      h = libtcod.random_get_int(0, self.room_min_size, self.room_max_size)
      x = libtcod.random_get_int(0, 0, dungeon.width - w - 1)
      y = libtcod.random_get_int(0, 0, dungeon.height - h - 1)

      new_room = Rect(x, y, w, h)
      failed = False
      for other_room in rooms:
        if new_room.intersect(other_room):
          failed = True
          break

      if not failed:
        self._create_room(dungeon, new_room)

        (new_x, new_y) = new_room.center()

        if len(rooms) == 0:
          start_x = new_x
          start_y = new_y
        else:
          (prev_x, prev_y) = rooms[len(rooms)-1].center()

          if libtcod.random_get_int(0, 0, 1) == 1:
            self._create_h_tunnel(dungeon, prev_x, new_x, prev_y)
            self._create_v_tunnel(dungeon, prev_y, new_y, new_x)
          else:
            self._create_v_tunnel(dungeon, prev_y, new_y, prev_x)
            self._create_h_tunnel(dungeon, prev_x, new_x, new_y)

        rooms.append(new_room)
    return (start_x, start_y)

  def _create_room(self, dungeon, room):
    for x in range(room.x1 + 1, room.x2):
      for y in range(room.y1 + 1, room.y2):
        dungeon.set_wall_on(x, y)

  def _create_h_tunnel(self, dungeon, x1, x2, y):
    for x in range(min(x1, x2), max(x1, x2) + 1):
      dungeon.set_wall_on(x, y)

  def _create_v_tunnel(self, dungeon, y1, y2, x):
    for y in range(min(y1, y2), max(y1, y2) + 1):
      dungeon.set_wall_on(x, y)

