package byteutil

import b64 "encoding/base64"
import hex "encoding/hex"

func HexToBytes(hexString string) []byte {
	if len(hexString)%2 == 1 {
		hexString = hexString + "0"
	}
	bytes, _ := hex.DecodeString(hexString)
	return bytes
}

func BytesToHex(bytes []byte) string {
	return hex.EncodeToString(bytes)
}

func BytesToBase64(bytes []byte) string {
	return b64.StdEncoding.EncodeToString(bytes)
}

func Base64ToBytes(base64String string) []byte {
	bytes, _ := b64.StdEncoding.DecodeString(base64String)
	return bytes
}

func HexToBase64(str string) string {
	return BytesToBase64(HexToBytes(str))
}
