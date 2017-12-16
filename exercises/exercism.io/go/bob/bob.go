package bob

import (
	"regexp"
	"strings"
)

func Hey(remark string) string {
	remark = strings.TrimSpace(remark)

	switch {
	case isAYell(remark) && isAQuestion(remark):
		return "Calm down, I know what I'm doing!"
	case isAYell(remark):
		return "Whoa, chill out!"
	case isAQuestion(remark):
		return "Sure."
	case isInSilence(remark):
		return "Fine. Be that way!"
	default:
		return "Whatever."
	}
}

func isAYell(remark string) bool {
	hasLetters, _ := regexp.MatchString("[A-Z]", remark)
	return hasLetters && remark == strings.ToUpper(remark)
}

func isAQuestion(remark string) bool {
	return strings.HasSuffix(remark, "?")
}

func isInSilence(remark string) bool {
	return remark == ""
}
