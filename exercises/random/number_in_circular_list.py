# Find if number exists in increasing circular linked list

class List:
  def __init__(self, value, next_node):
    self.value = value
    self.next_node = next_node


def make_circular_list(values):
  nodes = [List(value, None) for value in values]
  if not nodes:
    return None

  for i in range(len(nodes) - 1):
    nodes[i].next_node = nodes[i + 1]
  nodes[-1].next_node = nodes[0]

  return nodes[0]


def number_exists(list, number):
  node = list
  while True:
    if node == None or node.value > number:
      return False
    elif node.value == number:
      return True
    else:
      node = node.next_node
      if node == list:
        return False


assert not number_exists(make_circular_list([]), 42)

assert number_exists(make_circular_list([42]), 42)
assert not number_exists(make_circular_list([13]), 42)

assert number_exists(make_circular_list([1, 2, 3, 4, 5]), 4)
assert not number_exists(make_circular_list([1, 2, 3, 4, 5]), 20)
assert not number_exists(make_circular_list([10, 20, 30]), 11)
