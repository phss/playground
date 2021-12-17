from typing import Counter, Tuple

def parse(filename: str):
    with open(filename) as file:
        fileLines = file.read().split('\n')

        template = fileLines[0]

        rules = {}
        for line in fileLines[2:len(fileLines)]:
            a, b = line.split(' -> ')
            rules[a] = b

        return (template, rules) 

def solve(input: Tuple[str, dict[str, str]]) -> int:
    polymer, rules = input

    for _ in range(10):
        newPolymer = ""
        for i, first in enumerate(polymer[:-1]):
            second = polymer[i+1]
            pair = first + second
            newPolymer += first
            if pair in rules:
                newPolymer += rules[pair]
        newPolymer += polymer[-1]        
        polymer = newPolymer

    freqs = Counter(polymer)

    return max(freqs.values()) - min(freqs.values())

def main():
    input = parse('data/day14.txt')
    result = solve(input)
    print(result)

if __name__ == '__main__':
    main()