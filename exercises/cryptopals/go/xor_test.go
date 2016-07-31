package main

import "testing"

func TestXorHexStrings(t *testing.T) {
	assertEquals(t, "e82be3", xorHexStrings("abc123", "43eac0"))
}

func TestXorHexStringSingleByte(t *testing.T) {
	bytes := hexToBytes("abc123")
	assertEquals(t, "d3b95b", bytesToHex(xorBytesAndSingleByte(bytes, byte(120))))
}
