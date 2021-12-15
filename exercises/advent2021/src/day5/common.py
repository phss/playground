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