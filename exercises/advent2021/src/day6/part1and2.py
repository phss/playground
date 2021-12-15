import collections

def parse(filename: str) -> list[int]:
    with open(filename) as file:
        line = file.read()
        return list(map(int, line.split(',')))

def solve(input: list[int], days: int) -> int:
    fishes = {0:0, 1:0, 2:0, 3:0, 4:0, 5:0, 6:0, 7:0, 8:0}
    fishes.update(collections.Counter(input))

    for _ in range(days // 7):
        previousFishes = fishes.copy()
        for fishDay, amount in previousFishes.items():
            # if fishDay < 7:
            fishes[(fishDay+2)%9] += amount
        
        fishes[7] -= previousFishes[7]
        fishes[8] -= previousFishes[8]

    remainingDays = days % 7
    finalFishes = 0
    for fishDay, amount in fishes.items():
        if (fishDay - remainingDays) < 0:
            finalFishes += amount

    return sum(fishes.values()) + finalFishes

def main():
    input = parse('data/day6.txt')
    result = solve(input, 256)
    print(result)

if __name__ == '__main__':
    main()
