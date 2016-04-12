import sys, tempfile, os
from subprocess import call

EDITOR = os.environ.get('EDITOR','vim') #that easy!
filename = sys.argv[1]

preread = ""
with open(filename, "r") as f:
  preread = f.read()

with open(filename, "w") as f:
  f.write(preread)
  f.flush()
  call([EDITOR, f.name])
  
print open(filename).read()
