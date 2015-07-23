#!/usr/bin/env python
import sys
from bs4 import BeautifulSoup


def main(filename):
  checkstyle = BeautifulSoup(open(filename), 'xml')

  print "file,size,cyclomatic,fanout"
  for f in checkstyle.find_all("file"):
    name = f['name']
    size = 0
    cyclomatic = 0
    fanout = 0
    print "%s, %d, %d, %d" % (name, size, cyclomatic, fanout)

if __name__ == '__main__':
  main(sys.argv[1])
