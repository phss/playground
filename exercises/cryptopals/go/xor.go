package main

func xorHexStrings(strA, strB string) string {
	a := hexToBytes(strA)
	b := hexToBytes(strB)

	xor := xorBytes(a, b)

	return bytesToHex(xor)
}

func xorBytesAndSingleByte(bytes []byte, b byte) []byte {
	xor := make([]byte, len(bytes))
	for i, _ := range bytes {
		xor[i] = bytes[i] ^ b
	}
	return xor
}

func xorBytes(a, b []byte) []byte {
	xor := make([]byte, len(a))
	for i, _ := range a {
		xor[i] = a[i] ^ b[i]
	}
	return xor
}
