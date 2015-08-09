package main

import "fmt"
import "os"

func runeToDecimal(char rune) byte {
	dec := char - '0'
	if dec > 9 {
		dec = char - 'a' + 10
	}
	return byte(dec)
}

func hexToBytes(hexString string) []byte {
	decimals := make([]byte, len(hexString))
	for i, char := range hexString {
		decimals[i] = runeToDecimal(char)
	}
	fmt.Println(byte(10) + byte(5))
	return decimals
}

func hexToBase64(str string) string {
	return str
}

func main() {
	hexString := os.Args[1]

	fmt.Println(hexToBase64(hexString))

	fmt.Println(hexToBytes("4927ff"))
}
