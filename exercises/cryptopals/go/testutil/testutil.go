package testutil

import "testing"

func AssertEquals(t *testing.T, expected interface{}, actual interface{}) {
	if expected != actual {
		t.Errorf("Expected '%s', but got '%s'", expected, actual)
	}
}

func AssertBytesEquals(t *testing.T, expected []byte, actual []byte) {
	for i, _ := range expected {
		if actual[i] != expected[i] {
			t.Errorf("Expected byte at %d to be <%d>, but was <%d>", i, expected[i], actual[i])
		}
	}
}
