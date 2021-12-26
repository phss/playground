import heapq

def parse(filename: str):
    with open(filename) as file:
        fileLines = file.read().split('\n')
        tile = [list(map(int, list(line))) for line in fileLines]

        fullmap = []
        for i in range(5):
            for row in tile:
                fullrow = []
                for j in range(5):
                    fullrow += [(risk+i+j if (risk+i+j) <= 9 else (risk+i+j-9)) for risk in row]
                fullmap.append(fullrow)

        return fullmap
        


def solve(input: list[list[int]]) -> int:
    end = (len(input[0])-1, len(input)-1)
    paths = []
    heapq.heappush(paths, (0, (0, 0)))
    visited = set()

    while len(paths) > 0:
        risk, (x, y) = heapq.heappop(paths)
        visited.add((x, y))

        if x == end[0] and y == end[1]:
            return risk

        for next in [(x-1, y), (x+1, y), (x, y-1), (x, y+1)]:
            nextX, nextY = next
            if not (next in visited) and nextX >= 0 and nextX <= end[0] and nextY >= 0 and nextY <= end[1]:
                visited.add(next)
                heapq.heappush(paths, (risk + input[nextY][nextX], next))

    return -1

def main():
    input = parse('data/day15.txt')
    result = solve(input)
    print(result)

if __name__ == '__main__':
    main()