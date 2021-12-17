from collections import defaultdict

def parse(filename: str):
    with open(filename) as file:
        fileLines = file.read().split('\n')
        input = defaultdict(list)
        for line in fileLines:
            a, b = line.split("-")
            input[a].append(b)
            input[b].append(a)
        return input 

def canVisit(path: list[str], next: str) -> bool:
    if next == 'start':
        return False
    if next.isupper() or not (next in path):
        return True

    smallCaves = list(filter(lambda c: c.islower(), path))
    return len(smallCaves) == len(set(smallCaves))

def solve(input: dict[str, list[str]]) -> int:
    paths = 0
    inprogress = [['start']]

    while len(inprogress) > 0:
        path = inprogress.pop()
        for next in input[path[-1]]:
            if next == 'end':
                paths += 1
            elif canVisit(path, next):
                inprogress.append(path + [next])

    return paths

def main():
    input = parse('data/day12.txt')
    result = solve(input)
    print(result)

if __name__ == '__main__':
    main()