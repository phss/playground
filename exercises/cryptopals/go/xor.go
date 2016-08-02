package main

func xorBytes(a, b []byte) []byte {
	xor := make([]byte, max(len(a), len(b)))

	for i, _ := range xor {
		xor[i] = a[i%len(a)] ^ b[i%len(b)]
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
