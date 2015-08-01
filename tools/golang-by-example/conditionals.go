package main

import "fmt"

func main() {
	loop()
}

func loop() {
	fmt.Println("-- Infinite loop --")
	for {
		fmt.Println("infinite")
		break
	}

	fmt.Println("-- Sort of while loop --")
	i := 1
	for i < 4 {
		fmt.Println(i)
		i++
	}

	fmt.Println("-- Classic for loop --")
	for a := 0; a < 3; a++ {
		fmt.Println(a)
	}
}
