from __future__ import print_function
import re

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

def perform(instruction_str, display):
  if instruction_str.startswith('rect '):
    w, h = map(int, re.findall(r"\d+", instruction_str))
    display.add_rect(w, h)
  if instruction_str.startswith('rotate column '):
    c, s = map(int, re.findall(r"\d+", instruction_str))
    display.rotate_column(c, s)
  if instruction_str.startswith('rotate row '):
    r, s = map(int, re.findall(r"\d+", instruction_str))
    display.rotate_row(r, s)
  else:
    None

perform('rect 3x2', display)
perform('rotate column x=1 by 1', display)
perform('rotate row y=0 by 4', display)
perform('rotate column x=1 by 1', display)
display.show()
print(display.lit_pixels())
