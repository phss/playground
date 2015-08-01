package main

import "fmt"

func main() {
	loop()
	branching()
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

func branching() {
	fmt.Println("-- if/else --")
	if 2+2 == 4 {
		fmt.Println("Math works!!")
	} else {
		fmt.Println("Math fails :(")
	}

	fmt.Println("-- if/else if/else --")
	if 2+2 == 5 {
		fmt.Println("Math fails I think!!")
	} else if 2+3 == 1 {
		fmt.Println("Math fails again :(")
	} else {
		fmt.Println("Oh well...")
	}
}
