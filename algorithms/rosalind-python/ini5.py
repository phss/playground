out = open('output.txt', 'w')

for i, line in enumerate(open('input.txt', 'r')):
  if i % 2 == 1:
    out.write(line)
