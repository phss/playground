package byteutil

import (
	"bytes"
	"testing"
)
import tu "../testutil"

func TestHexToBase64Conversion(t *testing.T) {
	tu.AssertEquals(t, "ASNFZ4mrze8=", HexToBase64("0123456789abcdef"))
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
		actual := HexToBytes(c.input)
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
		actual := BytesToHex(c.input)
		tu.AssertEquals(t, c.expected, actual)
	}
}

func TestBytesToBase64Conversion(t *testing.T) {
	tu.AssertEquals(t, "SSfw", BytesToBase64([]byte{73, 39, 240}))
}

func TestBase64ToBytesConversion(t *testing.T) {
	if !bytes.Equal(Base64ToBytes("SSfw"), []byte{73, 39, 240}) {
		t.Errorf("Fail")
	}
}
