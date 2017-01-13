package byteutil

import "testing"
import tu "../testutil"

func TestXorBytes(t *testing.T) {
	cases := []struct {
		a        []byte
		b        []byte
		expected []byte
	}{
		{[]byte{40, 41, 42}, []byte{40, 41, 42}, []byte{0, 0, 0}},
		{[]byte{40, 41, 42}, []byte{0, 0, 0}, []byte{40, 41, 42}},
		{[]byte{40, 41, 42}, []byte{12, 32, 201}, []byte{36, 9, 227}},
		{[]byte{40, 41, 42}, []byte{12}, []byte{36, 37, 38}},
		{[]byte{12, 13}, []byte{40, 41, 42}, []byte{36, 36, 38}},
	}

	for _, c := range cases {
		actual := XorBytes(c.a, c.b)
		tu.AssertBytesEquals(t, c.expected, actual)
	}

}
