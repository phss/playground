from collections import defaultdict
from typing import Tuple

def parse(filename: str):
    with open(filename) as file:
        fileLines = file.read().split('\n')

        dots = set()
        folds = []
        stage = 'dots'
        for line in fileLines:
            if line == '':
                stage = 'folds'
            elif stage == 'dots':
                a, b = map(int, line.split(','))
                dots.add((a, b))
            else:
                foldStr = line.split(' ')[2]
                a, b = foldStr.split('=')
                folds.append((a, int(b)))

        return (dots, folds) 

def solve(input: Tuple[set[(int, int)], list[Tuple[str, int]]]) -> int:
    dots, folds = input

    newDots = set()
    axis, line = folds[0]
    for dot in dots:
        x, y = dot
        if (axis == 'x' and x < line) or (axis == 'y' and y < line):
            newDots.add((x, y))
        elif axis == 'x' and x > line:
            newDots.add((line - (x - line), y))
        elif axis == 'y' and y > line:
            newDots.add((x, line - (y - line)))

    return len(newDots)

def main():
    input = parse('data/day13.txt')
    result = solve(input)
    print(result)

if __name__ == '__main__':
    main()