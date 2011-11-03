fib := method(n, 
    if(n <= 2, 1, fib(n-2) + fib(n-1))
)

fibLoop := method(n, 
    prev := 1
    sum  := 1
    for(i, 2, n-1, 
        temp := sum
        sum = sum + prev
        prev = temp    
    )    
    sum
)

for(i, 1, 20, fib(i) println)
for(i, 1, 20, fibLoop(i) println)