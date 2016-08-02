package main

import "testing"

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
		actual := xorBytes(c.a, c.b)
		for i, _ := range c.expected {
			if actual[i] != c.expected[i] {
				t.Errorf("Expected byte at %d to be <%d>, but was <%d>", i, c.expected[i], actual[i])
			}
		}
	}

}
