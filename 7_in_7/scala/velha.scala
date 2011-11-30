class Board(rawBoard: List[String]) {

  def result = {
    val results = List(rowWinner(0),
                       rowWinner(1),
                       rowWinner(2),
                       colWinner(0),
                       colWinner(1),
                       colWinner(2),
                       diagonalUpDown(),
                       diagonalDownUp(),
                       "draw")

    results.dropWhile(_ == null).head
  }

  private def rowWinner(rowNumber: Int) = {
    matches(pos(rowNumber, 0), pos(rowNumber, 1), pos(rowNumber, 2))
  }

  private def colWinner(colNumber: Int) = {
    matches(pos(0, colNumber), pos(1, colNumber), pos(2, colNumber))
  }

  private def diagonalUpDown() = {
    matches(pos(0, 0), pos(1, 1), pos(2, 2))
  }

  private def diagonalDownUp() = {
    matches(pos(0, 2), pos(1, 1), pos(2, 0))
  }

  private def matches(a: String, b: String, c: String) = {
    if ((a == b) && (a == c)) a else null
  }

  private def pos(row: Int, col: Int) = {
    rawBoard(row * 3 + col)
  }
}

def result(board: List[String]) = {
  new Board(board).result
}


val xWins = List("X", "X", "X",
                 "O", " ", "O",
                 "O", "O", " ")

val oWins = List("O", "X", "O",
                 "X", "O", "X",
                 "O", " ", "X")
                 
val aDraw = List("O", "X", "X",
                 "X", "O", "O",
                 "O", "X", "X")

val play  = List("X", "O", "X",
                 "O", "O", "X",
                 "X", "O", " ")                 

println(result(xWins))
println(result(oWins))
println(result(aDraw))
println(result(play))