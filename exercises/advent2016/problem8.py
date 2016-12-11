from __future__ import print_function

class Display:
  def __init__(self, width, height):
    self.width = width
    self.height = height
    self.pixels = [['.'] * width for _ in range(height)]

  def show(self):
    for row in self.pixels:
      for elem in row:
        print(elem, end='')
      print()
    print()

  def add_rect(self, width, height):
    for x in range(width):
      for y in range(height):
        self.pixels[y][x] = '#'

  def rotate_column(self, col, shift):
    new_col = [None] * self.height

    for y in range(self.height):
      new_col[(y + shift) % self.height] = self.pixels[y][col]

    for y in range(self.height):
      self.pixels[y][col] = new_col[y]

  def rotate_row(self, row, shift):
    new_row = [None] * self.width

    for x in range(self.width):
      new_row[(x + shift) % self.width] = self.pixels[row][x]

    self.pixels[row] = new_row

  def lit_pixels(self):
    lit = 0
    for row in self.pixels:
      lit += row.count('#')
    return lit


display = Display(7, 3)

display.add_rect(3, 2)
display.show()

display.rotate_column(1, 1)
display.show()

display.rotate_row(0, 4)
display.show()

display.rotate_column(1, 1)
display.show()

print(display.lit_pixels())
