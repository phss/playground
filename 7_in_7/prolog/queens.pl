valid_queen((Row, Col)) :- Range = [1, 2, 3, 4, 5, 6, 7, 8], member(Row, Range), member(Col, Range).

valid_board([]).
valid_board([H|T]) :- valid_queen(H), valid_board(T).

rows([],[]).
rows([(Row, _)|QT], [Row|RT]) :- rows(QT, RT).

cols([],[]).
cols([(_, Col)|QT], [Col|CT]) :- cols(QT, CT).

swdiags([], []).
swdiags([(Row, Col)|QT], [Diagonal|DT]) :-
  Diagonal is Col - Row,
  swdiags(QT, DT).

nediags([], []).
nediags([(Row, Col)|QT], [Diagonal|DT]) :-
  Diagonal is Col + Row,
  nediags(QT, DT).  

queens(Board) :- 
  length(Board, 8),
  valid_board(Board),

  rows(Board, Rows),
  cols(Board, Cols),
  swdiags(Board, SWDiags),
  nediags(Board, NEDiags),

  fd_all_different(Rows),
  fd_all_different(Cols),
  fd_all_different(SWDiags),
  fd_all_different(NEDiags).