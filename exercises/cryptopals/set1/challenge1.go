package main

import "fmt"
import "os"

func hexToBase64(str string) string {
	return str
}

func main() {
	hexString := os.Args[1]

	fmt.Println(hexToBase64(hexString))
}
