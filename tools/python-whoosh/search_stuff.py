import sys, string
import whoosh.index as index
from whoosh.fields import *
from whoosh.qparser import QueryParser

search_query = string.join(sys.argv[1:])
ix = index.open_dir("index")
with ix.searcher() as searcher:
  query = QueryParser("content", ix.schema).parse(search_query)
  results = searcher.search(query)
  for result in results:
    print result
