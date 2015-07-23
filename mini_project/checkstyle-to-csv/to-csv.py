#! /usr/bin/env python

from bs4 import BeautifulSoup

checkstyle = BeautifulSoup(open('checkstyle-output.xml'), "xml")

print checkstyle
