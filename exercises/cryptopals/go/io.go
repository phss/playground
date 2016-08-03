package main

import (
	"bufio"
	"os"
	"strings"
)

func readLines(filename string) []string {
	lines := make([]string, 0)

	file, _ := os.Open(filename)
	defer file.Close()

	scanner := bufio.NewScanner(file)
	scanner.Split(bufio.ScanLines)
	for scanner.Scan() {
		line_raw := scanner.Text()
		trimmed_line := strings.TrimSpace(line_raw)
		lines = append(lines, trimmed_line)
	}

	return lines
}
