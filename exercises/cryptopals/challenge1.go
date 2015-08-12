package main

import "fmt"

func challenge1() {
	hexString := "49276d206b696c6c696e6720796f757220627261696e206c696b65206120706f69736f6e6f7573206d757368726f6f6d"

	fmt.Println("Set 1, Challenge 1:", hexToBase64(hexString))
}

func main() {
	challenge1()
}
