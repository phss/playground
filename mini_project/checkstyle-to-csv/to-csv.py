#!/usr/bin/env python
import sys
from bs4 import BeautifulSoup


def main(filename):
  checkstyle = BeautifulSoup(open(filename), 'xml')

  print checkstyle

if __name__ == '__main__':
  main(sys.argv[1])
