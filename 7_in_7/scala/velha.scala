def result(board: List[String]) = {
  "nothing"
}


val xWins = List("X", "X", "X",
                 "O", " ", "O",
                 "O", "O", " ")

val oWins = List("O", "X", "X",
                 "X", "O", "X",
                 "O", " ", "O")
                 
val aDraw = List("O", "X", "X",
                 "X", "O", "O",
                 "O", "X", "X")

println(result(xWins))
println(result(oWins))
println(result(aDraw))