def whileLoop {
  println("While:")
  var i = 1
  while (i <= 3) {
    println(i)
    i += 1
  }
  
}

def forLoop(stuff: List[Integer]) {
  println("Java for:")
  for (i <-0 until stuff.length) {
    println(stuff(i))
  }
}

def rubyLoop(stuff: List[Integer]) {
  println("Ruby style loop:")
  stuff.foreach { thing => println(thing) }
}

whileLoop
forLoop(List(2, 5, 22))
rubyLoop(List(32, 1, 232))