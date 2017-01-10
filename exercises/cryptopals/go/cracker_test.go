package main

import "testing"
import tu "./testutil"

func TestHammingDistance(t *testing.T) {
	tu.AssertEquals(t, 37, hammingDistance([]byte("this is a test"), []byte("wokka wokka!!!")))
}
