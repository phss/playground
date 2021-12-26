from typing import Tuple
import math


infinity = float('inf')

def parse(filename: str):
    with open(filename) as file:
        fileLines = file.read().split('\n')
        heightmap = []
        for fileLine in fileLines:
            heightmap.append([int(char) for char in fileLine])
        return heightmap

def findBasin(input: list[list[int]], riskPoint: Tuple[int, int]) -> set[(int, int)]:
    basin = set()
    basin.add(riskPoint)

    def shouldAdd(point, riskHeight):
        x, y = point
        withinBounds = x >= 0 and x < len(input[0]) and y >= 0 and y < len(input)
        appropriateHeight = withinBounds and input[y][x] > riskHeight and input[y][x] < 9
        return withinBounds and appropriateHeight and not (point in basin)

    nextPoints = basin.copy()
    while len(nextPoints) > 0:
        point = nextPoints.pop()
        x, y = point
        height = input[y][x]

        for newPoint in [(x-1, y), (x+1, y), (x, y-1), (x, y+1)]:
            if shouldAdd(newPoint, height):
                basin.add(newPoint)
                nextPoints.add(newPoint)

    return basin

def basins(input: list[list[int]], riskPoints: list[(int, int)]) -> list[int]:
    basins = [findBasin(input, point) for point in riskPoints]
    return map(len, basins)

def solve(input: list[list[int]]):
    riskPoints = []
    for y, row in enumerate(input):
        for x, height in enumerate(row):
            left = input[y][x-1] if x > 0 else infinity
            right = input[y][x+1] if x < len(row)-1 else infinity
            up = input[y-1][x] if y > 0 else infinity
            down = input[y+1][x] if y < len(input)-1 else infinity
            
            if height < left and height < right and height < up and height < down:
                riskPoints.append((x, y))

    

    basinSizes = list(reversed(sorted(basins(input, riskPoints))))
    return math.prod(basinSizes[0:3])

def main():
    input = parse('data/day9.txt')
    result = solve(input)
    print(result)

if __name__ == '__main__':
    main()