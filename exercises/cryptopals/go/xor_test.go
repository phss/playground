package main

import "testing"

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
		{[]byte{40, 41, 42}, []byte{12, 32, 201}, []byte{36, 9, 227}},
	}

	for _, c := range cases {
		actual := xorBytes(c.a, c.b)
		for i, _ := range c.expected {
			if actual[i] != c.expected[i] {
				t.Errorf("Expected byte at %d to be <%d>, but was <%d>", i, c.expected[i], actual[i])
			}
		}
	}

}
