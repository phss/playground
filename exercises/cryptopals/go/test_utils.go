package main

import "testing"

func assertEquals(t *testing.T, expected interface{}, actual interface{}) {
	if expected != actual {
		t.Errorf("Expected '%s', but got '%s'", expected, actual)
	}
}