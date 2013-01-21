a = 4405
b = 9341

s = 0
for n in range(a, b+1):
  if n % 2 == 1:
    s += n

print s

# Very fancy pants solution
print sum(range(a|1, b+1, 2))
