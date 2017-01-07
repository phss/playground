package main

type crackResult struct {
	message string
	key     byte
	score   int
}

func crackSingleByteXorCipher(hexString string) (result crackResult) {
	bytes := hexToBytes(hexString)
	for i := 0; i < 256; i++ {
		key := byte(i)
		xor := xorBytes(bytes, []byte{key})
		message := string(xor)
		score := simpleEnglishScoring(message)
		if score > result.score {
			result = crackResult{message, key, score}
		}
	}
	return
}

func simpleEnglishScoring(str string) int {
	english := []byte("etaoin")
	score := 0

	for _, char := range str {
		b := byte(char)
		for _, eb := range english {
			if b == eb {
				score++
			}
		}
	}

	return score
}

func hammingDistance(a, b string) int {
	aBytes := []byte(a)
	bBytes := []byte(b)
	diff := 0

	for i, _ := range aBytes {
		for j := 0; j < 8; j++ {
			mask := byte(1 << uint(j))
			if (aBytes[i] & mask) != (bBytes[i] & mask) {
				diff++
			}
		}
	}

	return diff
}
