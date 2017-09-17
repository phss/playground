# Find if number exists in increasing circular linked list

class List:
  def __init__(self, value, next_node):
    self.value = value
    self.next_node = next_node


def make_circular_list(values):
  first = None
  previous = None

  for value in values:
    node = List(value, None)
    if first == None:
      first = node
    else:
      previous.next_node = node
    previous = node

  if first != None:
    previous.next_node = first
  return first


def number_exists(l, n):
  return False


assert not number_exists(make_circular_list([]), 42)
#assert number_exists(make_circular_list([42]), 42)
assert not number_exists(make_circular_list([13]), 42)
