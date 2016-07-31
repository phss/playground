package main

import "testing"

func TestXorHexStrings(t *testing.T) {
	assertEquals(t, "e82be3", xorHexStrings("abc123", "43eac0"))
}

func TestXorHexStringSingleByte(t *testing.T) {
	assertEquals(t, "d3b95b", xorHexStringAndSingleByte("abc123", byte(120)))
}
