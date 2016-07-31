package main

import "testing"
import "bytes"

func TestHexToBase64Conversion(t *testing.T) {
	assertEquals(t, "ASNFZ4mrze8=", hexToBase64("0123456789abcdef"))
}

func TestHexToBytesConversion(t *testing.T) {
	cases := []struct {
		input    string
		expected []byte
	}{
		{"4927ff", []byte{73, 39, 255}},
		{"4927f", []byte{73, 39, 240}},
	}

	for _, c := range cases {
		actual := hexToBytes(c.input)
		if !bytes.Equal(actual, c.expected) {
			t.Errorf("Expected '%s', but got '%s'", c.expected, actual)
		}
	}
}

func TestBytesToHexConversion(t *testing.T) {
	cases := []struct {
		input    []byte
		expected string
	}{
		{[]byte{73, 39, 255}, "4927ff"},
		{[]byte{73, 39, 240}, "4927f0"},
	}

	for _, c := range cases {
		actual := bytesToHex(c.input)
		assertEquals(t, c.expected, actual)
	}
}
