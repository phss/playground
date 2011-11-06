cat(mancha).
cat(lobinho).
cat(coia).
cat(macarena).
cat(peludo).

dog(pingo).
dog(lady).
dog(duke).

bibichao(duke).
bibichao(mancha).

bibichao_kitten(X) :- cat(X), bibichao(X).

mae(coia, mancha).
mae(coia, peludo).

irmao(X, Y) :- \+(X = Y), mae(Z, X), mae(Z, Y).