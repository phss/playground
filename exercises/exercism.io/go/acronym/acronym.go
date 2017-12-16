package acronym

import (
	"strings"
)

func Abbreviate(term string) string {
	words := strings.Split(term, " ")
	abbreviation := ""
	for _, word := range words {
		abbreviation += strings.ToUpper(string(word[0]))
	}
	return abbreviation
}
