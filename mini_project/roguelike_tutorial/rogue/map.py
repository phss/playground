import libtcod.libtcodpy as libtcod

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

  def build_rooms_on(self, dungeon, map_width, map_height):
    rooms = []

    for r in range(self.max_rooms):
      w = libtcod.random_get_int(0, self.room_min_size, self.room_max_size)
      h = libtcod.random_get_int(0, self.room_min_size, self.room_max_size)
      x = libtcod.random_get_int(0, 0, map_width - w - 1)
      y = libtcod.random_get_int(0, 0, map_height - h - 1)

      new_room = Rect(x, y, w, h)
      failed = False
      for other_room in rooms:
        if new_room.intersect(other_room):
          failed = True
          break

      if not failed:
        self.__create_room(dungeon, new_room)

        (new_x, new_y) = new_room.center()

        if len(rooms) == 0:
          start_x = new_x
          start_y = new_y
        else:
          (prev_x, prev_y) = rooms[len(rooms)-1].center()

          if libtcod.random_get_int(0, 0, 1) == 1:
            self.__create_h_tunnel(dungeon, prev_x, new_x, prev_y)
            self.__create_v_tunnel(dungeon, prev_y, new_y, new_x)
          else:
            self.__create_v_tunnel(dungeon, prev_y, new_y, prev_x)
            self.__create_h_tunnel(dungeon, prev_x, new_x, new_y)

        rooms.append(new_room)
    return (start_x, start_y)

  def __create_room(self, dungeon, room):
    for x in range(room.x1 + 1, room.x2):
      for y in range(room.y1 + 1, room.y2):
        dungeon[x][y].set_wall()

  def __create_h_tunnel(self, dungeon, x1, x2, y):
    for x in range(min(x1, x2), max(x1, x2) + 1):
      dungeon[x][y].set_wall()

  def __create_v_tunnel(self, dungeon, y1, y2, x):
    for y in range(min(y1, y2), max(y1, y2) + 1):
      dungeon[x][y].set_wall()

def make_dungeon(map_width, map_height, room_min_size, room_max_size, max_rooms):
  dungeon = [[ Tile(True) for y in range(map_height)] for x in range(map_width)]
  room_maker = RoomMaker(room_min_size, room_max_size, max_rooms)
  start_pos = room_maker.build_rooms_on(dungeon, map_width, map_height)

  return (dungeon, start_pos)
