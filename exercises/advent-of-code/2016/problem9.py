import re

def decompressed_size(input):
  decompressed = 0

  i = 0
  while i < len(input):
    if input[i] != '(':
      decompressed += 1
      i += 1
    else:
      e = input.find(')', i) + 1
      marker = input[i:e]
      size, times = map(int, re.findall(r"\d+", marker))
      decompressed += decompressed_size(input[e:e+size]) * times
      i = e + size

  return decompressed

#print decompressed_size('X(8x2)(3x3)ABCY') # 20
#print decompressed_size('(27x12)(20x12)(13x14)(7x10)(1x12)A') # 241920

with open('files/problem9.txt', 'r') as f:
    input = f.read().rstrip()

print decompressed_size(input)
