package main

import (
	"bufio"
	"fmt"
	"os"
	"regexp"
	"strconv"
	"strings"
)

type board [][]string

type input struct {
	numbers []string
	boards  []board
}

func check(err error) {
	if err != nil {
		panic(err)
	}
}

func parseFile(filename string) input {
	file, err := os.Open(filename)
	check(err)
	defer file.Close()

	fileContents := make([]string, 0)
	scanner := bufio.NewScanner(file)
	for scanner.Scan() {
		line := scanner.Text()
		if line != "" {
			fileContents = append(fileContents, scanner.Text())
		}
	}

	totalBoards := (len(fileContents) - 1) / 5
	boards := make([]board, totalBoards)
	space := regexp.MustCompile(`\s+`)
	for i := 0; i < totalBoards; i++ {
		newBoard := make(board, 5)
		for j := 0; j < 5; j++ {
			boardLine := fileContents[(i*5)+j+1]
			newBoard[j] = space.Split(space.ReplaceAllString(strings.Trim(boardLine, " "), " "), -1)
		}
		boards = append(boards, newBoard)
	}

	return input{
		numbers: strings.Split(fileContents[0], ","),
		boards:  boards,
	}
}

func markOn(board *board, number string) {
	for i := 0; i < len(*board); i++ {
		for j := 0; j < len((*board)[i]); j++ {
			if (*board)[i][j] == number {
				(*board)[i][j] = "M"
			}
		}
	}
}

func checkWin(board *board) bool {
	for i := 0; i < len(*board); i++ {
		horizontalMarks := 0
		verticalMarks := 0
		for j := 0; j < len((*board)[i]); j++ {
			if (*board)[i][j] == "M" {
				horizontalMarks++
			}
			if (*board)[j][i] == "M" {
				verticalMarks++
			}
		}
		if horizontalMarks == len((*board)[i]) || verticalMarks == len(*board) {
			return true
		}
	}
	return false
}

func calculateSolution(board *board, number string) int {
	sum := 0
	for i := 0; i < len(*board); i++ {
		for j := 0; j < len((*board)[i]); j++ {
			if (*board)[i][j] != "M" {
				boardNum, err := strconv.Atoi((*board)[i][j])
				check(err)
				sum += boardNum
			}
		}
	}

	numberNum, err := strconv.Atoi(number)
	check(err)

	return sum * numberNum
}

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
