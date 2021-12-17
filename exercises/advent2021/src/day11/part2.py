def parse(filename: str):
    with open(filename) as file:
        fileLines = file.read().split('\n')
        return [list(map(int, list(line))) for line in fileLines]

def pp(input: list[list[int]]):
    for row in input:
        print("".join(map(str, row)))
    print("--")

def solve(input: list[list[int]]) -> int:
    flashes = 0

    def increase(x, y):
        if y < 0 or y >= len(input) or x < 0 or x >= len(input[y]) or input[y][x] > 9:
            return
        input[y][x] += 1
        if input[y][x] > 9:
            increase(x-1, y-1)
            increase(x, y-1)
            increase(x+1, y-1)
            increase(x-1, y)
            increase(x+1, y)
            increase(x-1, y+1)
            increase(x, y+1)
            increase(x+1, y+1)

    for step in range(2000):
        stepFlashes = 0

        for y, row in enumerate(input):
            for x, octopus in enumerate(input[y]):
                increase(x, y)
                
        for y, row in enumerate(input):
            for x, octopus in enumerate(input[y]):
                if input[y][x] > 9:
                    flashes += 1
                    stepFlashes += 1
                    input[y][x] = 0

        if stepFlashes == 100:
            return step+1

    return -1

def main():
    input = parse('data/day11.txt')
    result = solve(input)
    print(result)

if __name__ == '__main__':
    main()