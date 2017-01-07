package main

import b64 "encoding/base64"
import hex "encoding/hex"

func hexToBytes(hexString string) []byte {
	if len(hexString)%2 == 1 {
		hexString = hexString + "0"
	}
	bytes, _ := hex.DecodeString(hexString)
	return bytes
}

func bytesToHex(bytes []byte) string {
	return hex.EncodeToString(bytes)
}

func bytesToBase64(bytes []byte) string {
	return b64.StdEncoding.EncodeToString(bytes)
}
func base64ToBytes(base64String string) []byte {
	bytes, _ := b64.StdEncoding.DecodeString(base64String)
	return bytes
}

func hexToBase64(str string) string {
	return bytesToBase64(hexToBytes(str))
}
