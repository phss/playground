package main

import "testing"

func TestHexToBase64Conversion(t *testing.T) {
	hexInputString := "49276d206b696c6c696e6720796f757220627261696e206c696b65206120706f69736f6e6f7573206d757368726f6f6d"
	expectedBase64String := "SSdtIGtpbGxpbmcgeW91ciBicmFpbiBsaWtlIGEgcG9pc29ub3VzIG11c2hyb29t"
	actualBase64String := hexToBase64(hexInputString)
	if expectedBase64String != actualBase64String {
		t.Errorf("Expected '%s', but got '%s'", expectedBase64String, actualBase64String)
	}
}
