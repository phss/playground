package main

import (
	"bufio"
	"fmt"
	"os"
	"strconv"
)

func solve(input []int) int {
	set := make(map[int]bool)
	for _, entry := range input {
		remaining := 2020 - entry
		if set[remaining] {
			return entry * remaining
		}

		set[entry] = true
	}

	return 0
}

func readInput(inputFilepath string) ([]int, error) {
	inputFile, err := os.Open(inputFilepath)
	if err != nil {
		return nil, err
	}
	defer inputFile.Close()

	var input []int
	scanner := bufio.NewScanner(inputFile)
	for scanner.Scan() {
		entry, err := strconv.Atoi(scanner.Text())
		if err != nil {
			return nil, err
		}

		input = append(input, entry)
	}

	if err := scanner.Err(); err != nil {
		return nil, err
	}

	return input, nil
}

func main() {
	if len(os.Args) != 2 {
		fmt.Println("Wrong number of arguments")
		os.Exit(1)
	}

	input, err := readInput(os.Args[1])
	if err != nil {
		panic(err)
	}

	answer := solve(input)
	fmt.Printf("Solution: %d\n", answer)
}
