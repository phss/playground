TwoDimList := Object clone do (
    init := method(self data := List clone)
    dim := method(x, y, for(i, 0, x-1, data append(list() setSize(y))); self)
    set := method(x, y, value, data at(x) atPut(y, value); self)
    get := method(x, y, data at(x) at(y))
    toFile := method(filename, 
        File remove(filename)
    )
)

dim := method(x, y, TwoDimList clone dim(x,y))


multi := dim(2, 2) set(0, 0, "a") set(0, 1, "b") set(1, 0, "c") set(1, 1, "d")
multi get(1, 0) println
multi toFile("test.csv")