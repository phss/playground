valid([]).
valid([H|T]) :- fd_all_different(H), valid(T).

sudoku(Puzzle, Solution) :- 
  Solution = Puzzle,

  Puzzle = [S11, S12, S13, S14, S15, S16, S17, S18, S19,
            S21, S22, S23, S24, S25, S26, S27, S28, S29,
            S31, S32, S33, S34, S35, S36, S37, S38, S39,
            S41, S42, S43, S44, S45, S46, S47, S48, S49,
            S51, S52, S53, S54, S55, S56, S57, S58, S59,
            S61, S62, S63, S64, S65, S66, S67, S68, S69,
            S71, S72, S73, S74, S75, S76, S77, S78, S79,
            S81, S82, S83, S84, S85, S86, S87, S88, S89,
            S91, S92, S93, S94, S95, S96, S97, S98, S99],
  
  fd_domain(Solution, 1, 9),

  Row1 = [S11, S12, S13, S14, S15, S16, S17, S18, S19],
  Row2 = [S21, S22, S23, S24, S25, S26, S27, S28, S29],
  Row3 = [S31, S32, S33, S34, S35, S36, S37, S38, S39],
  Row4 = [S41, S42, S43, S44, S45, S46, S47, S48, S49],
  Row5 = [S51, S52, S53, S54, S55, S56, S57, S58, S59],
  Row6 = [S61, S62, S63, S64, S65, S66, S67, S68, S69],
  Row7 = [S71, S72, S73, S74, S75, S76, S77, S78, S79],
  Row8 = [S81, S82, S83, S84, S85, S86, S87, S88, S89],
  Row9 = [S91, S92, S93, S94, S95, S96, S97, S98, S99],

  Col1 = [S11, S21, S31, S41, S51, S61, S71, S81, S91],
  Col2 = [S12, S22, S32, S42, S52, S62, S72, S82, S92],
  Col3 = [S13, S23, S33, S43, S53, S63, S73, S83, S93],
  Col4 = [S14, S24, S34, S44, S54, S64, S74, S84, S94],
  Col5 = [S15, S25, S35, S45, S55, S65, S75, S85, S95],
  Col6 = [S16, S26, S36, S46, S56, S66, S76, S86, S96],
  Col7 = [S17, S27, S37, S47, S57, S67, S77, S87, S97],
  Col8 = [S18, S28, S38, S48, S58, S68, S78, S88, S98],
  Col9 = [S19, S29, S39, S49, S59, S69, S79, S89, S99],

  Sqr1 = [S11, S12, S13, S21, S22, S23, S31, S32, S33],
  Sqr2 = [S14, S15, S16, S24, S25, S26, S34, S35, S36],
  Sqr3 = [S17, S18, S19, S27, S28, S29, S37, S38, S39],
  Sqr4 = [S41, S42, S43, S51, S52, S53, S61, S62, S63],
  Sqr5 = [S44, S45, S46, S54, S55, S56, S64, S65, S66],
  Sqr6 = [S47, S48, S49, S57, S58, S59, S67, S68, S69],
  Sqr7 = [S71, S72, S73, S81, S82, S83, S91, S92, S93],
  Sqr8 = [S74, S75, S76, S84, S85, S86, S94, S95, S96],
  Sqr9 = [S77, S78, S79, S87, S88, S89, S97, S98, S99],
  

  valid([Row1, Row2, Row3, Row4, Row5, Row6, Row7, Row8, Row9,
         Col1, Col2, Col3, Col4, Col5, Col6, Col7, Col8, Col9,
         Sqr1, Sqr2, Sqr3, Sqr4, Sqr5, Sqr6, Sqr7, Sqr8, Sqr9]),

  write(S11), write(S12), write(S13), write('|'), write(S14), write(S15), write(S16), write('|'), write(S17), write(S18), write(S19), nl,
  write(S21), write(S22), write(S23), write('|'), write(S24), write(S25), write(S26), write('|'), write(S27), write(S28), write(S29), nl,
  write(S31), write(S32), write(S33), write('|'), write(S34), write(S35), write(S36), write('|'), write(S37), write(S38), write(S39), nl,
  write(S41), write(S42), write(S43), write('|'), write(S44), write(S45), write(S46), write('|'), write(S47), write(S48), write(S49), nl,
  write(S51), write(S52), write(S53), write('|'), write(S54), write(S55), write(S56), write('|'), write(S57), write(S58), write(S59), nl,
  write(S61), write(S62), write(S63), write('|'), write(S64), write(S65), write(S66), write('|'), write(S67), write(S68), write(S69), nl,
  write(S71), write(S72), write(S73), write('|'), write(S74), write(S75), write(S76), write('|'), write(S77), write(S78), write(S79), nl,
  write(S81), write(S82), write(S83), write('|'), write(S84), write(S85), write(S86), write('|'), write(S87), write(S88), write(S89), nl,
  write(S91), write(S92), write(S93), write('|'), write(S94), write(S95), write(S96), write('|'), write(S97), write(S98), write(S99).