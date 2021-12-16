def parse(filename: str) -> list[int]:
    with open(filename) as file:
        line = file.read()
        return list(map(int, line.split(',')))

def solve(input: list[int]) -> int:
    input.sort()
    end = len(input) - 1

    cost = 0
    for i in range(end//2):
        cost += input[end-i] - input[i]

    return cost

def main():
    input = parse('data/day7.txt')
    result = solve(input)
    print(result)

if __name__ == '__main__':
    main()