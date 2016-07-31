package main

func score(str string) float64 {
	english := []byte("etaoin")
	score := 0.0

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

func crackSingleByteXorCipher(hexString string) (
	decryptedMessage string,
	decryptionKey byte,
	highestScore float64) {
	for i := 0; i < 256; i++ {
		key := byte(i)
		xor := xorHexStringAndSingleByte(hexString, key)
		strBytes := hexToBytes(xor)
		message := string(strBytes)
		score := score(message)
		if score > highestScore {
			decryptedMessage = message
			decryptionKey = key
			highestScore = score
		}
	}
	return
}
