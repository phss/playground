"Guess a number from 1-100" println
number := (Random value * 100) floor + 1

previousGuessProximity := nil
10 repeat(
    writeln("Guess: ")
    guess := File standardInput() readLine() asNumber
    
    if(guess == number, "You got it!" println; break)    

    guessProximity := (guess - number) abs
    
    if(previousGuessProximity != nil, if(guessProximity < previousGuessProximity, "Hotter" println, "Colder" println))    
    previousGuessProximity = guessProximity
)