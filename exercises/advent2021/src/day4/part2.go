package main

import (
	"fmt"
)

func solve(input input) int {
	remainingBoards := len(input.boards)
	boardWon := make(map[int]bool)

	for _, number := range input.numbers {
		for bi, board := range input.boards {
			markOn(&board, number)
			if !boardWon[bi] && checkWin(&board) {
				boardWon[bi] = true
				remainingBoards -= 1
				if remainingBoards == 0 {
					return calculateSolution(&board, number)
				}
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
