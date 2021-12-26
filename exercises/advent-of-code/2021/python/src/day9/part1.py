infinity = float('inf')

def parse(filename: str):
    with open(filename) as file:
        fileLines = file.read().split('\n')
        heightmap = []
        for fileLine in fileLines:
            heightmap.append([int(char) for char in fileLine])
        return heightmap

def solve(input: list[list[int]]):
    risk = 0
    for y, row in enumerate(input):
        for x, height in enumerate(row):
            left = input[y][x-1] if x > 0 else infinity
            right = input[y][x+1] if x < len(row)-1 else infinity
            up = input[y-1][x] if y > 0 else infinity
            down = input[y+1][x] if y < len(input)-1 else infinity
            
            if height < left and height < right and height < up and height < down:
                risk += height + 1

    return risk

def main():
    input = parse('data/day9.txt')
    result = solve(input)
    print(result)

if __name__ == '__main__':
    main()