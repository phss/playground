def parse(filename: str) -> list[int]:
    with open(filename) as file:
        line = file.read()
        return list(map(int, line.split(',')))

def costCalc(distance: int) -> int:
    absDist = abs(distance)
    return absDist*(absDist+1)//2

def solve(input: list[int]) -> int:
    input.sort()
    start = min(input)
    end = max(input)
    minCost = float('inf')

    for i in range(start, end+1):
        cost = sum([costCalc(crab - i) for crab in input])
        if cost < minCost:
            minCost = cost

    return minCost

def main():
    input = parse('data/day7.txt')
    result = solve(input)
    print(result)

if __name__ == '__main__':
    main()