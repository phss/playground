package main

import "fmt"

func main() {
	for {
		fmt.Println("infinite")
		break
	}

	i := 1
	for i < 4 {
		fmt.Println(i)
		i++
	}

	for a := 0; a < 3; a++ {
		fmt.Println(a)
	}
}
