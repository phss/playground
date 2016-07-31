package main

import "fmt"

func xorHex(str_a, str_b string) string {
	a := hexToBytes(str_a)
	b := hexToBytes(str_b)
	xor := make([]byte, len(a))

	for i, _ := range a {
		xor[i] = a[i] ^ b[i]
	}

	fmt.Println(xor)
	return "blah"
}