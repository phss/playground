import whoosh.index as index
from whoosh.fields import *
from whoosh.qparser import QueryParser

ix = index.open_dir("index")
with ix.searcher() as searcher:
  query = QueryParser("content", ix.schema).parse("first")
  results = searcher.search(query)
  print results[0]
