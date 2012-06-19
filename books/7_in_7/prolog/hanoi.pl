move(1, From, To, _) :- 
  write('Move disk from '),
  write(From),
  write(' to '),
  write(To),
  nl.

move(Disks, From, To, Buffer) :-
  Disks > 1,
  OtherDisks is Disks - 1,
  move(OtherDisks, From, Buffer, To),
  move(1, From, To, _),
  move(OtherDisks, Buffer, To, From).