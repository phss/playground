import re

def decompress(input):
  decompressed = ""

  i = 0
  while i < len(input):
    char = input[i]
    if char != '(':
      decompressed += char
      i += 1
    else:
      e = input.find(')', i) + 1
      marker = input[i:e]
      size, times = map(int, re.findall(r"\d+", marker))
      decompressed += input[e:e+size] * times
      i = e + size

  return decompressed

print decompress('ADVENT')
print decompress('A(1x5)BC')
print decompress('(3x3)XYZ')
print decompress('(6x1)(1x3)A')
print decompress('X(8x2)(3x3)ABCY')
