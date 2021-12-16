from dataclasses import dataclass

@dataclass
class Signal:
    patterns: list[str]
    output: list[str]

def normalize(patterns: list[str]) -> list[str]:
    return ["".join(sorted(p)) for p in patterns]

def parse(filename: str) -> list[Signal]:
    with open(filename) as file:
        fileLines = file.read().split('\n')

        signals = []
        for fileLine in fileLines:
            patternsStr, signalStr = fileLine.split(' | ')
            signals.append(Signal(normalize(patternsStr.split()), normalize(signalStr.split())))

        return signals

def containsAll(pattern: str, elements: str) -> bool:
    return set(elements).issubset(set(pattern))

def containsOnlyOne(pattern: str, elements: str) -> bool:
    return len(set(elements).intersection(set(pattern))) == 1

def doesNotContain(pattern: str, elements: str) -> bool:
    return len(set(elements).intersection(set(pattern))) == 0

def remove(pattern: str, elements: str) -> str:
    return "".join(set(pattern).difference(set(elements)))

def patternMap(patterns: list[str]):
    one = [p for p in patterns if len(p) == 2][0]
    four = [p for p in patterns if len(p) == 4][0]
    seven = [p for p in patterns if len(p) == 3][0]
    eight = [p for p in patterns if len(p) == 7][0]
    fourPattern = remove(four, one)

    fiveLen = [p for p in patterns if len(p) == 5]
    three = [p for p in fiveLen if containsAll(p, one)][0]
    five = [p for p in fiveLen if containsAll(p, fourPattern)][0]
    two = [p for p in fiveLen if p != three and p != five][0]

    sixLen = [p for p in patterns if len(p) == 6]
    six = [p for p in sixLen if containsOnlyOne(p, one)][0]
    nine = [p for p in sixLen if p != six and containsAll(p, fourPattern)][0]
    zero = [p for p in sixLen if p != nine and p != six][0]

    return {
        one: 1,
        two: 2,
        three: 3,
        four: 4,
        five: 5,
        six: 6,
        seven: 7,
        eight: 8,
        nine: 9,
        zero: 0
    }

def outputValue(signal: Signal) -> int:
    patterns = patternMap(signal.patterns)
    outputStr = "".join(map(str, [patterns[digit] for digit in signal.output]))
    return int(outputStr) 

def solve(input: list[Signal]) -> int:
    return sum([outputValue(signal) for signal in input])

def main():
    input = parse('data/day8.txt')
    result = solve(input)
    print(result)

if __name__ == '__main__':
    main()