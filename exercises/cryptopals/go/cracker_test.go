package main

import "testing"

func TestHammingDistance(t *testing.T) {
	assertEquals(t, 37, hammingDistance("this is a test", "wokka wokka!!!"))
}
