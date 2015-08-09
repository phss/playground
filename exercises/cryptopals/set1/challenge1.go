package main

import "fmt"
import "os"
import b64 "encoding/base64"

func runeToDecimal(char rune) byte {
	dec := char - '0'
	if dec > 9 {
		dec = char - 'a' + 10
	}
	return byte(dec)
}

func hexToBytes(hexString string) []byte {
	decimals := make([]byte, len(hexString)/2)
	for i := 0; i < len(hexString); i += 2 {
		j := i + 1
		decimals[i/2] = runeToDecimal(rune(hexString[i]))*16 + runeToDecimal(rune(hexString[j]))
	}
	return decimals
}

func bytesToBase64(bytes []byte) string {
	return b64.StdEncoding.EncodeToString(bytes)
}

func hexToBase64(str string) string {
	return bytesToBase64(hexToBytes(str))
}

func main() {
	hexString := os.Args[1]

	fmt.Println(hexToBase64(hexString))

	//fmt.Println(bytesToBase64(hexToBytes("4927ff")))
}
