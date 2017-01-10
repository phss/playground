package main

import (
	"strings"
	"testing"
)
import tu "./testutil"
import bu "./byteutil"

func TestSet1Challenge1(t *testing.T) {
	expectedBase64String := "SSdtIGtpbGxpbmcgeW91ciBicmFpbiBsaWtlIGEgcG9pc29ub3VzIG11c2hyb29t"
	actualBase64String := bu.HexToBase64("49276d206b696c6c696e6720796f757220627261696e206c696b65206120706f69736f6e6f7573206d757368726f6f6d")

	tu.AssertEquals(t, expectedBase64String, actualBase64String)
}

func TestSet1Challenge2(t *testing.T) {
	expectedXor := "746865206b696420646f6e277420706c6179"
	actualXor := bu.BytesToHex(
		bu.XorBytes(
			bu.HexToBytes("1c0111001f010100061a024b53535009181c"),
			bu.HexToBytes("686974207468652062756c6c277320657965")))

	tu.AssertEquals(t, expectedXor, actualXor)
}

func TestSet1Challenge3(t *testing.T) {
	hexEncodedString := "1b37373331363f78151b7f2b783431333d78397828372d363c78373e783a393b3736"
	result := crackSingleByteXorCipher(hexEncodedString)

	tu.AssertEquals(t, "Cooking MC's like a pound of bacon", result.message)
	tu.AssertEquals(t, byte(88), result.key)
}

func TestSet1Challenge4(t *testing.T) {
	lines := readLines("files/4.txt")

	jobs := make(chan string, len(lines))
	results := make(chan crackResult, len(lines))

	for _, line := range lines {
		jobs <- line
	}
	close(jobs)

	for w := 0; w <= 5; w++ {
		func() {
			for line := range jobs {
				results <- crackSingleByteXorCipher(line)
			}
		}()
	}

	var bestResult crackResult

	for i := 0; i < len(lines); i++ {
		result := <-results
		if result.score > bestResult.score {
			bestResult = result
		}
	}

	tu.AssertEquals(t, "Now that the party is jumping\n", bestResult.message)
	tu.AssertEquals(t, byte(53), bestResult.key)
}

func TestSet1Challenge5(t *testing.T) {
	phrase := "Burning 'em, if you ain't quick and nimble\nI go crazy when I hear a cymbal"
	key := "ICE"

	expected := "0b3637272a2b2e63622c2e69692a23693a2a3c6324202d623d63343c2a26226324272765272a282b2f20430a652e2c652a3124333a653e2b2027630c692b20283165286326302e27282f"

	actual := bu.BytesToHex(bu.XorBytes([]byte(phrase), []byte(key)))

	tu.AssertEquals(t, expected, actual)
}

func TestSet1Challenge6(t *testing.T) {
	encrypted := bu.Base64ToBytes(strings.Join(readLines("files/6.txt"), ""))

	key := crackRepeatedKeyXorCipher(encrypted)

	tu.AssertEquals(t, "Terminator X: Bring the noise", string(key))
}
