package main

func crackSingleByteXorCipher(hexString string) (
	decryptedMessage string,
	decryptionKey byte,
	highestScore int) {
	bytes := hexToBytes(hexString)
	for i := 0; i < 256; i++ {
		key := byte(i)
		xor := xorBytes(bytes, []byte{key})
		message := string(xor)
		score := simple_english_scoring(message)
		if score > highestScore {
			decryptedMessage = message
			decryptionKey = key
			highestScore = score
		}
	}
	return
}

func simple_english_scoring(str string) int {
	english := []byte("etaoin")
	score := 0

	for _, char := range str {
		b := byte(char)
		for _, eb := range english {
			if b == eb {
				score += 1
			}
		}
	}

	return score
}
