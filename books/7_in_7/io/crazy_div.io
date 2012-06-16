Number div := Number getSlot("/")
Number / := method(n, if(n==0, 0, call target div(n)))

(20 / 3) println
(20 / 0) println