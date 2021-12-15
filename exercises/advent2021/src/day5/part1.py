from dataclasses import dataclass

@dataclass
class Point:
    x: int
    y: int

@dataclass
class Line:
    a: Point
    b: Point


def parse(filename: str) -> list[Line]:
    with open(filename) as file:
        fileLines = file.read().split('\n')

        lines = []
        for fileLine in fileLines:
            a, b = fileLine.split(' -> ')
            ax, ay = map(int, a.split(','))
            bx, by = map(int, b.split(','))
            lines.append(Line(Point(ax, ay), Point(bx, by)))

        return lines

def solve(input: list[Line], size: int) -> int:
    board = [ [0]*size for i in range(size)]

    for line in input:
        if line.a.x == line.b.x:
            begin, end = min(line.a.y, line.b.y), max(line.a.y, line.b.y)
            for y in range(begin, end+1):
                board[y][line.a.x] += 1 
        elif line.a.y == line.b.y:
            begin, end = min(line.a.x, line.b.x), max(line.a.x, line.b.x)
            for x in range(begin, end+1):
                board[line.a.y][x] += 1 

    dangerPoints = 0        
    for y in range(size):
        for x in range(size):
            if board[y][x] > 1:
                dangerPoints += 1

    return dangerPoints

def main():
    input = parse('data/day5.txt')
    result = solve(input, 1000)
    print(result)

if __name__ == '__main__':
    main()