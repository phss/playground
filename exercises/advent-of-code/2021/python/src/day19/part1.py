
def parse(filename: str):
    with open(filename) as file:
        fileLines = file.read().split('\n')
        scanners = []
        currentScanner = None
        for line in fileLines:
            if line.startswith('---'):
                currentScanner = []
            elif line == "":
                scanners.append(currentScanner)
            else:
                a, b, c = map(int, line.split(","))
                currentScanner.append((a, b, c))

        scanners.append(currentScanner)
        return scanners

def diff(a, b):
    return (b[0]-a[0], b[1]-a[1], b[2]-a[2])

def overlap(a, b):
    for tryB in b:
        for tryA in a:
            shift = diff(tryA, tryB)
            count = 0
            for coord in b:
                if (diff(shift, coord)) in a:
                    count += 1
                    if count >= 12:
                        break
            if count >= 12:
                return (True, shift)
    return (False, None)

def transform(scanner, shift):
    return [diff(shift, coord) for coord in scanner]
    
def rotations(initial):
    instructions = [
        ([0, 1, 2], [[1, 1, 1], [1, -1, -1], [-1, 1, -1], [-1, -1, 1]], [[1, 1, -1], [1, -1, 1], [-1, 1, 1], [-1, -1, -1]]),
        ([1, 0, 2], [[1, 1, -1], [1, -1, 1], [-1, 1, 1], [-1, -1, -1]], [[1, 1, 1], [1, -1, -1], [-1, 1, -1], [-1, -1, +1]]),
        ([2, 0, 1], [[1, 1, 1], [1, -1, -1], [-1, 1, -1], [-1, -1, 1]], [[1, 1, -1], [1, -1, 1], [-1, 1, 1], [-1, -1, -1]])
    ]
    rots = []
    for instruction in instructions:
        order, flips, invertedFlips = instruction
        x, y, z = order
        for flip in flips:
            flipX, flipY, flipZ = flip
            rots.append([(coord[x]*flipX, coord[y]*flipY, coord[z]*flipZ) for coord in initial])
        for flip in invertedFlips:
            flipX, flipY, flipZ = flip
            rots.append([(coord[x]*flipX, coord[z]*flipY, coord[y]*flipZ) for coord in initial])
    return rots

def any_overlap(a, b):
    for i, rot in enumerate(rotations(b)):
        overlapped, diff = overlap(a, rot)
        if overlapped:
            return (True, rot, diff)
    return (False, None, None)

def solve(input) -> int:
    beacons, rest = set(input[0]), input[1:]

    while len(rest) > 0:
        for scanner in rest:
            overlapped, rotated_scanner, shift = any_overlap(beacons, scanner)
            if overlapped:
                beacons = beacons.union(set(transform(rotated_scanner, shift)))
                rest.remove(scanner)

    return len(beacons)

def main():
    input = parse("data/day19.txt")
    result = solve(input)
    print(result)

if __name__ == '__main__':
    main()