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

def pp(dots: set[(int, int)]):
    width = max(map(lambda dot: dot[0], dots)) + 1
    height = max(map(lambda dot: dot[1], dots)) + 1
    
    for y in range(height):
        for x in range(width):
            if (x, y) in dots:
                print('#', end='')
            else:
                print('.', end='')
        print('|')

def solve(input: Tuple[set[(int, int)], list[Tuple[str, int]]]) -> int:
    dots, folds = input

    for fold in folds:
        newDots = set()
        axis, line = fold
        for dot in dots:
            x, y = dot
            if (axis == 'x' and x < line) or (axis == 'y' and y < line):
                newDots.add((x, y))
            elif axis == 'x' and x > line:
                newDots.add((line - (x - line), y))
            elif axis == 'y' and y > line:
                newDots.add((x, line - (y - line)))
        dots = newDots

    pp(dots)

    return len(dots)

def main():
    input = parse('data/day13.txt')
    result = solve(input)
    print(result)

if __name__ == '__main__':
    main()