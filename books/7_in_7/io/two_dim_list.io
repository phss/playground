TwoDimList := Object clone do (
    init := method(self data := List clone)
    dim := method(x, y, for(i, 0, x-1, data append(list() setSize(y))); self)
    set := method(x, y, value, data at(x) atPut(y, value); self)
    get := method(x, y, data at(x) at(y))
    toFile := method(filename, 
        File open(filename) remove close
        
        file := File open(filename) 
        file write(data size asString, " ", data at(0) size asString, "\n")    
        data foreach(row, row foreach(column, file write(column, " ")); file write("\n"))
        file close
    )
    fromFile := method(filename,
        file := File openForReading(filename)
        header := file readLine split
        fileData := file readLines

        dimList := TwoDimList clone dim(header at(0) asNumber, header at(1) asNumber)
        for(i, 0, fileData size - 1,
            row := fileData at(i) split
            for(j, 0, row size - 1, dimList set(i, j, row at(j)))
        )

        dimList
    )
)

dim := method(x, y, TwoDimList clone dim(x,y))


multi := dim(2, 3) set(0, 0, "a") set(0, 1, "b") set(0, 2, "x") set(1, 0, "c") set(1, 1, "d") set(1, 2, "y")
//multi get(1, 0) println
multi toFile("test.csv")

from_file := TwoDimList fromFile("test.csv")
from_file get(1, 2) println