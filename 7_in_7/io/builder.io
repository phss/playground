Builder := Object clone

Builder ident := ""
Builder forward := method(
  self ident := self ident .. " "
  writeln(self ident, "<", call message name, ">")
  call message arguments foreach(
    arg, 
    content := self doMessage(arg); 
    if(content type == "Sequence", writeln(self ident .. " ", content))
  )
  writeln(self ident, "</", call message name, ">")
  self ident := self ident exSlice(1)
  nil
)

Builder  ul(
  li("Io"), 
  li("Lua"), 
  li("JavaScript"),
  test(
    a("b")
  )
)
