package main

import (
	"fmt"
)

func solve(input input) int {
	for _, number := range input.numbers {
		for _, board := range input.boards {
			markOn(&board, number)
			if checkWin(&board) {
				return calculateSolution(&board, number)
			}
		}
	}
	return -1
}

func main() {
	input := parseFile("data/day4.txt")
	solution := solve(input)
	fmt.Println(solution)
}
