package main

func xorHexStrings(strA, strB string) string {
	a := hexToBytes(strA)
	b := hexToBytes(strB)
	xor := make([]byte, len(a))

	for i, _ := range a {
		xor[i] = a[i] ^ b[i]
	}

	return bytesToHex(xor)
}
