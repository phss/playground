from whoosh.index import create_in
from whoosh.fields import *

schema = Schema(filename=TEXT(stored=True), content=TEXT)

ix = create_in("index", schema)
writer = ix.writer()
writer.add_document(filename=u"blah", content=u"This is the first document we've added!")
writer.add_document(filename=u"another", content=u"The second one is even more interesting!")
writer.commit()
