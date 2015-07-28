package main

import "fmt"
import "math"

func main() {
	const n = 10
	// n = 2 // would fail, cannot be reassigned

	fmt.Println(math.Sin(n))

	const d = 3e20 / n
	fmt.Println(d)
	// fmt.Println(int64(d)) // error, overflow
	fmt.Println(int64(d / 100000))
}
