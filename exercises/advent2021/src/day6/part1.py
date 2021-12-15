def parse(filename: str) -> list[int]:
    with open(filename) as file:
        line = file.read()
        return list(map(int, line.split(',')))

def solve(fishes: list[int], days: int) -> int:
    for _ in range(days):
        for i in range(len(fishes)):
            fishes[i] -= 1
            if fishes[i] < 0:
                fishes[i] = 6
                fishes.append(8)

    return len(fishes)

def main():
    input = parse('data/day6.txt')
    result = solve(input, 80)
    print(result)

if __name__ == '__main__':
    main()