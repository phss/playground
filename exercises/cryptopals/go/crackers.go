package main

import bu "./byteutil"

type crackResult struct {
	message string
	key     byte
	score   int
}

func crackSingleByteXorCipher(hexString string) (result crackResult) {
	bytes := bu.HexToBytes(hexString)
	for i := 0; i < 256; i++ {
		key := byte(i)
		xor := bu.XorBytes(bytes, []byte{key})
		message := string(xor)
		score := simpleEnglishScoring(message)
		if score > result.score {
			result = crackResult{message, key, score}
		}
	}
	return
}

func crackRepeatedKeyXorCipher(encrypted []byte) []byte {
	guessedKeysize := guessXorKeysize(encrypted)
	key := make([]byte, guessedKeysize)

	for i := 0; i < guessedKeysize; i++ {
		block := make([]byte, 0)
		j := i
		for j < len(encrypted) {
			block = append(block, encrypted[j])
			j += guessedKeysize
		}

		key[i] = crackSingleByteXorCipher(bu.BytesToHex(block)).key
	}

	return key
}

func guessXorKeysize(data []byte) int {
	bestGuess := -1
	bestEditDistance := 10000000.0

	for guess := 2; guess <= 40; guess++ {
		first := data[0:guess]
		sumDistances := 0
		slices := 1
		for (guess * (slices + 1)) < len(data) {
			sumDistances += hammingDistance(data[guess*slices:guess*(slices+1)], first)
			slices++
		}
		normalisedEditDistance := float64(sumDistances) / float64(slices) / float64(guess)

		if normalisedEditDistance < bestEditDistance {
			bestGuess = guess
			bestEditDistance = normalisedEditDistance
		}
	}
	return bestGuess
}

func simpleEnglishScoring(str string) int {
	english := []byte("etaoinsh")
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

func hammingDistance(aBytes, bBytes []byte) int {
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
