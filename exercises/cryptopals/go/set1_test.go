package main

import (
	"bufio"
	"os"
	"strings"
	"testing"
)

func TestSet1Challenge1(t *testing.T) {
	expectedBase64String := "SSdtIGtpbGxpbmcgeW91ciBicmFpbiBsaWtlIGEgcG9pc29ub3VzIG11c2hyb29t"
	actualBase64String := hexToBase64("49276d206b696c6c696e6720796f757220627261696e206c696b65206120706f69736f6e6f7573206d757368726f6f6d")

	assertEquals(t, expectedBase64String, actualBase64String)
}

func TestSet1Challenge2(t *testing.T) {
	expectedXor := "746865206b696420646f6e277420706c6179"
	actualXor := bytesToHex(
		xorBytes(
			hexToBytes("1c0111001f010100061a024b53535009181c"),
			hexToBytes("686974207468652062756c6c277320657965")))

	assertEquals(t, expectedXor, actualXor)
}

func TestSet1Challenge3(t *testing.T) {
	hexEncodedString := "1b37373331363f78151b7f2b783431333d78397828372d363c78373e783a393b3736"
	decryptedMessage, byteKey, _ := crackSingleByteXorCipher(hexEncodedString)

	assertEquals(t, "Cooking MC's like a pound of bacon", decryptedMessage)
	assertEquals(t, byte(88), byteKey)
}

func TestSet1Challenge4(t *testing.T) {
	bestMessage := ""
	bestKey := byte(0)
	bestScore := 0
	file, _ := os.Open("files/4.txt")
	defer file.Close()
	scanner := bufio.NewScanner(file)
	scanner.Split(bufio.ScanLines)
	for scanner.Scan() {
		message, key, score := crackSingleByteXorCipher(scanner.Text())
		if score > bestScore {
			bestScore = score
			bestMessage = strings.TrimSpace(message)
			bestKey = key
		}
	}

	assertEquals(t, "Now that the party is jumping", bestMessage)
	assertEquals(t, byte(53), bestKey)
}
