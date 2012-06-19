smallest([H], H).
smallest([H1, H2], H1) :- H1 < H2.
smallest([H1, H2], H2) :- H1 >= H2.
smallest([H1|[H2|T]], R) :- \+(T = []), smallest([H1, H2], SR), smallest([SR|T], R).