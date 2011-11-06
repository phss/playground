smaller(X, Y, X) :- X < Y.
smaller(X, Y, Y) :- X >= Y.

smallest([H], H).
smallest([H|T], R) :- smallest(T, SR), smaller(SR, H, R).