from dataclasses import dataclass

@dataclass
class Signal:
    patterns: list[str]
    output: list[str]

def parse(filename: str) -> list[Signal]:
    with open(filename) as file:
        fileLines = file.read().split('\n')

        signals = []
        for fileLine in fileLines:
            patternsStr, signalStr = fileLine.split(' | ')
            signals.append(Signal(patternsStr.split(), signalStr.split()))

        return signals

def solve(input: list[Signal]) -> int:
    digits = 0
    for signal in input:
        for digit in signal.output:
            if len(digit) != 5 and len(digit) != 6:
                digits += 1
    return digits

def main():
    input = parse('data/day8.txt')
    result = solve(input)
    print(result)

if __name__ == '__main__':
    main()