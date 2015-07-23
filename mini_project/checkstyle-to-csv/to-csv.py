#!/usr/bin/env python
import sys, re
from bs4 import BeautifulSoup

size_regexp =  re.compile("File length is (\d+)")

def file_size(xml):
  error_message = xml.find(source="com.puppycrawl.tools.checkstyle.checks.sizes.FileLengthCheck")['message']
  return size_regexp.match(error_message).group(1)

def main(filename):
  checkstyle = BeautifulSoup(open(filename), 'xml')

  print "file,size,cyclomatic,fanout"
  for f in checkstyle.find_all("file"):
    name = f['name']
    size = file_size(f) 
    cyclomatic = 0
    fanout = 0
    print "%s, %s, %d, %d" % (name, size, cyclomatic, fanout)

if __name__ == '__main__':
  main(sys.argv[1])
