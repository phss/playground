import sys
from sys import stdin, stdout

n, k = map(int, stdin.readline().split())
d = 0

for _ in range(n):
  t = int(stdin.readline())
  if t % k == 0: d += 1

stdout.write(str(d) + "\n")
