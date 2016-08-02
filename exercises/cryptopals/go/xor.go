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

func max(a, b int) int {
	if a > b {
		return a
	} else {
		return b
	}
}

func xorBytes(a, b []byte) []byte {
	xor := make([]byte, max(len(a), len(b)))

	for i := 0; i < size; i++ {
		xor[i] = a[i%len(a)] ^ b[i%len(b)]
	}

	return xor
}
