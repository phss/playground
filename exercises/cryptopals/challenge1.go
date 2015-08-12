package main

import "fmt"
import "os"

func main() {
	hexString := os.Args[1]

	fmt.Println(hexToBase64(hexString))

	fmt.Println(hexToBytes("4927ff"))
}
