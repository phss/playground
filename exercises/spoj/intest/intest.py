import sys
import psyco
psyco.full()

def main():
  from sys import stdin, stdout
  n, k = map(int, stdin.readline().split())
  d = 0

  for t in stdin.readlines():
    if int(t) % k == 0: d += 1

  stdout.write(str(d) + "\n")

if __name__ == "__main__":
  main()
