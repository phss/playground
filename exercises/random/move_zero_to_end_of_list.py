# Move zeros to end of array

def find_last_non_zero_index_from(array, i):
  while i > 0 and array[i] == 0:
    i -= 1
  return i


def move_zeros_to_end(array):
  i = 0
  last_non_zero_index = find_last_non_zero_index_from(array, len(array) - 1)

  while i < last_non_zero_index:
    if array[i] == 0:
      array[i], array[last_non_zero_index] = array[last_non_zero_index], array[i]
      last_non_zero_index = find_last_non_zero_index_from(array, last_non_zero_index)

    i += 1

  return array


assert move_zeros_to_end([]) == []
assert move_zeros_to_end([1]) == [1]
assert move_zeros_to_end([0]) == [0]
assert move_zeros_to_end([1, 2, 3]) == [1, 2, 3]
assert move_zeros_to_end([1, 2, 0]) == [1, 2, 0]
assert move_zeros_to_end([0, 0, 0]) == [0, 0, 0]
assert move_zeros_to_end([0, 1]) == [1, 0]
assert move_zeros_to_end([0, 1, 0, 2]) == [2, 1, 0, 0]
assert move_zeros_to_end([0, 0, 1, 2]) == [2, 1, 0, 0]
