smaller_and_rest(X, Y, Others, X, [Y|Others]) :- X < Y.
smaller_and_rest(X, Y, Others, Y, [X|Others]) :- X >= Y.

smallest_and_rest([Only], Only, []).
smallest_and_rest([Head|Tail], Smallest, Rest) :- 
  smallest_and_rest(Tail, TailSmallest, TailRest), 
  smaller_and_rest(Head, TailSmallest, TailRest, Smallest, Rest).

mysort([H], [H]).
mysort(List, [Smallest|Subresult]) :- 
  smallest_and_rest(List, Smallest, Rest),
  mysort(Rest, Subresult).