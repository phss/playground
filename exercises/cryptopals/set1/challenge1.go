package main

import "fmt"
import "os"
import b64 "encoding/base64"

func hexCharToByte(char rune) byte {
	dec := char - '0'
	if dec > 9 {
		dec = char - 'a' + 10
	}
	return byte(dec)
}

func hexToBytes(hexString string) []byte {
	chars := []rune(hexString)
	if len(chars)%2 == 1 {
		chars = append(chars, rune('0'))
	}
	bytes := make([]byte, (len(chars))/2)

	for i := 0; i < len(chars); i += 2 {
		j := i + 1
		bytes[i/2] = hexCharToByte(chars[i])*16 + hexCharToByte(chars[j])
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
