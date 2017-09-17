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
  node = l
  while True:
    if node == None or node.value > n:
      return False
    elif node.value == n:
      return True
    else:
      node = node.next_node
      if node == l:
        return False


assert not number_exists(make_circular_list([]), 42)

assert number_exists(make_circular_list([42]), 42)
assert not number_exists(make_circular_list([13]), 42)

assert number_exists(make_circular_list([1, 2, 3, 4, 5]), 4)
assert not number_exists(make_circular_list([1, 2, 3, 4, 5]), 20)
assert not number_exists(make_circular_list([10, 20, 30]), 11)
