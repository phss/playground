#!/usr/bin/env python
import sys, re
from bs4 import BeautifulSoup
from collections import namedtuple

Metric = namedtuple("Metric", "checkstyle_class regexp")
size_metric = Metric("com.puppycrawl.tools.checkstyle.checks.sizes.FileLengthCheck", re.compile("File length is (\d+)"))
fanout_metric = Metric("com.puppycrawl.tools.checkstyle.checks.metrics.ClassFanOutComplexityCheck", re.compile("Class Fan-Out Complexity is (\d+)"))
cyclomatic_metric = Metric("com.puppycrawl.tools.checkstyle.checks.metrics.CyclomaticComplexityCheck", re.compile("Cyclomatic Complexity is (\d+)"))


def compile_metric(xml, metric):
  max_value = 0
  for error in xml.find_all(source=metric.checkstyle_class):
    value = int(metric.regexp.match(error['message']).group(1))
    if value > max_value:
      max_value = value
  return max_value


def main(filename):
  checkstyle = BeautifulSoup(open(filename), 'xml')

  print "file,size,cyclomatic,fanout"
  for f in checkstyle.find_all("file"):
    name = f['name']
    size = compile_metric(f, size_metric)
    cyclomatic = compile_metric(f, cyclomatic_metric)
    fanout = compile_metric(f, fanout_metric)
    print "%s, %d, %d, %d" % (name, size, cyclomatic, fanout)

if __name__ == '__main__':
  main(sys.argv[1])
