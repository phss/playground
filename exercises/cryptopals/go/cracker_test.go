package main

import "testing"

func TestHammingDistance(t *testing.T) {
	assertEquals(t, 37, hammingDistance([]byte("this is a test"), []byte("wokka wokka!!!")))
}
