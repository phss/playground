revert([], []).
revert([Head|Tail], R) :- revert(Tail, Z), append(Z, [Head], R).