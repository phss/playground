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

func xorHexStringAndSingleByte(str string, b byte) string {
	strBytes := hexToBytes(str)
	xor := make([]byte, len(strBytes))

	for i, _ := range strBytes {
		xor[i] = strBytes[i] ^ b
	}

	return bytesToHex(xor)
}
