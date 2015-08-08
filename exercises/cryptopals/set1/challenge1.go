package main

import "fmt"

func hexToBase64(str string) string {
	return str
}

func main() {
	str := "49276d206b696c6c696e6720796f757220627261696e206c696b65206120706f69736f6e6f7573206d757368726f6f6d"

	fmt.Println(hexToBase64(str))
}
