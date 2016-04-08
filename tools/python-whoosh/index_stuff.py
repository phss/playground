import sys
import whoosh.index as index
from whoosh.fields import *

filename = sys.argv[1]
contents = open(filename).read()

ix = index.open_dir("index")
writer = ix.writer()
writer.add_document(filename=unicode(filename), content=unicode(contents, "utf-8"))
writer.commit()
