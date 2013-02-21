n, k = map(int, raw_input().split())
d = 0

for _ in range(n):
  t = int(raw_input())
  if t % k == 0: d += 1

print d
