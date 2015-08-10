package main

import "fmt"
import "os"
import b64 "encoding/base64"

func charToDecimal(char uint8) byte {
	dec := char - '0'
	if dec > 9 {
		dec = char - 'a' + 10
	}
	return byte(dec)
}

func hexToBytes(hexString string) []byte {
	bytes := make([]byte, len(hexString)/2)
	for i := 0; i < len(hexString); i += 2 {
		j := i + 1
		bytes[i/2] = charToDecimal(hexString[i])*16 + charToDecimal(hexString[j])
	}
	return bytes
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

	fmt.Println(hexToBytes("4927ff"))
}
