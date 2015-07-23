#!/usr/bin/env python
import sys
from bs4 import BeautifulSoup


def main(filename):
  checkstyle = BeautifulSoup(open(filename), 'xml')

  for f in checkstyle.find_all("file"):
    name = f['name']
    print name

if __name__ == '__main__':
  main(sys.argv[1])
