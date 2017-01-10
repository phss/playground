package testutil

import "testing"

func AssertEquals(t *testing.T, expected interface{}, actual interface{}) {
	if expected != actual {
		t.Errorf("Expected '%s', but got '%s'", expected, actual)
	}
}
