from whoosh.index import create_in
from whoosh.fields import *

schema = Schema(filename=TEXT(stored=True), content=TEXT)

ix = create_in("index", schema)
writer = ix.writer()
writer.commit()
