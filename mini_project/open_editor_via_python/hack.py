import sys, tempfile, os
from subprocess import call

EDITOR = os.environ.get('EDITOR','vim') #that easy!
filename = sys.argv[1]

preread = ""
if os.path.isfile(filename):
  preread = open(filename, "r").read()

with open(filename, "w") as f:
  f.write(preread)
  f.flush()
  call([EDITOR, f.name])
  
print open(filename).read()
