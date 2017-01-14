package crypto

import (
	"crypto/aes"
)

func DecryptAes128Ecb(data, key []byte) []byte {
	cipher, _ := aes.NewCipher([]byte(key))
	bs := 16
	i := 0
	plaintext := make([]byte, len(data))
	finalplaintext := make([]byte, len(data))
	for len(data) > 0 {
		cipher.Decrypt(plaintext, data)
		data = data[bs:]
		decryptedBlock := plaintext[:bs]
		for index, element := range decryptedBlock {
			finalplaintext[(i*bs)+index] = element
		}
		i++
		plaintext = plaintext[bs:]
	}
	return finalplaintext[:len(finalplaintext)-5]
}
