import whoosh.index as index
from whoosh.fields import *

ix = index.open_dir("index")
writer = ix.writer()
writer.add_document(filename=u"blah", content=u"This is the first document we've added!")
writer.add_document(filename=u"another", content=u"The second one is even more interesting!")
writer.commit()
