package main

import "fmt"

func main() {
	arrays()
}

func arrays() {
	var a [5]int
	fmt.Println("empty:", a)

	a[0] = 42
	a[3] = 30
	fmt.Println("something:", a)

	b := [5]int{1, 2, 3, 4, 5}
	fmt.Println("init directly:", b)
}
