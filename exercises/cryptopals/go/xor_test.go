package main

import (
	"bytes"
	"testing"
)

func TestXorHexStrings(t *testing.T) {
	assertEquals(t, "e82be3", xorHexStrings("abc123", "43eac0"))
}

func TestXorHexStringSingleByte(t *testing.T) {
	bytes := hexToBytes("abc123")
	assertEquals(t, "d3b95b", bytesToHex(xorBytesAndSingleByte(bytes, byte(120))))
}

func TestXorBytes(t *testing.T) {
	cases := []struct {
		a        []byte
		b        []byte
		expected []byte
	}{
		{[]byte{40, 41, 42}, []byte{40, 41, 42}, []byte{0, 0, 0}},
		{[]byte{40, 41, 42}, []byte{0, 0, 0}, []byte{40, 41, 42}},
		//{[]byte{40, 41, 42}, []byte{12, 32, 201}, []byte{40, 41, 42}},
	}

	for _, c := range cases {
		actual := xorBytes(c.a, c.b)
		if !bytes.Equal(actual, c.expected) {
			t.Errorf("Expected '%s', but got '%s'", c.expected, actual)
		}
	}

}
