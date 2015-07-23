#!/usr/bin/env python
import sys, re
from bs4 import BeautifulSoup
from collections import namedtuple

Metric = namedtuple("Metric", "checkstyle_class regexp")
size_metric = Metric("com.puppycrawl.tools.checkstyle.checks.sizes.FileLengthCheck", re.compile("File length is (\d+)"))
fanout_metric = Metric("com.puppycrawl.tools.checkstyle.checks.metrics.ClassFanOutComplexityCheck", re.compile("Class Fan-Out Complexity is (\d+)"))


def compile_metric(xml, metric):
  max_value = 0
  for error in xml.find_all(source=metric.checkstyle_class):
    value = int(metric.regexp.match(error['message']).group(1))
    if value > max_value:
      max_value = value
  return max_value

def file_size(xml):
  return compile_metric(xml, size_metric)

def file_fanout(xml):
  return compile_metric(xml, fanout_metric)

def main(filename):
  checkstyle = BeautifulSoup(open(filename), 'xml')

  print "file,size,cyclomatic,fanout"
  for f in checkstyle.find_all("file"):
    name = f['name']
    size = file_size(f) 
    cyclomatic = 0
    fanout = file_fanout(f) 
    print "%s, %d, %d, %d" % (name, size, cyclomatic, fanout)

if __name__ == '__main__':
  main(sys.argv[1])
