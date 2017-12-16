package bob

import (
	"regexp"
	"strings"
)

func Hey(remark string) string {
	remark = strings.TrimSpace(remark)

	if isAYell(remark) && isAQuestion(remark) {
		return "Calm down, I know what I'm doing!"
	} else if isAYell(remark) {
		return "Whoa, chill out!"
	} else if isAQuestion(remark) {
		return "Sure."
	} else if isInSilence(remark) {
		return "Fine. Be that way!"
	} else {
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
