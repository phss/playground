import sys, string
import whoosh.index as index
from whoosh.fields import *
from whoosh.qparser import QueryParser
from whoosh.query import FuzzyTerm, Term, Or

params = sys.argv[1:]
ix = index.open_dir("index")
with ix.searcher() as searcher:
  terms = [Term("content", word) for word in params]
  query = Or(terms)
  print query
  results = searcher.search(query)
  for result in results:
    print result
