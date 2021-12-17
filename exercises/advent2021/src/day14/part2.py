from collections import defaultdict
from functools import cache
from typing import Counter, Tuple

def parse(filename: str):
    with open(filename) as file:
        fileLines = file.read().split('\n')

        template = fileLines[0]

        rules = {}
        for line in fileLines[2:len(fileLines)]:
            a, b = line.split(' -> ')
            a1, a2 = list(a)
            rules[(a1, a2)] = b

        return (template, rules) 

def solve(input: Tuple[str, dict[(str, str), str]], steps: int) -> int:
    polymer, rules = input
    freqs = Counter(polymer)

    @cache
    def gen(pair: Tuple[str, str], step: int) -> Counter:
        nextElement = rules[pair]
        blah = Counter({nextElement: 1})
        if step == steps:
            return blah
        else:
            return gen((pair[0], nextElement), step + 1) + gen((nextElement, pair[1]), step + 1) + blah

    for i, first in enumerate(polymer[:-1]):
        second = polymer[i+1]
        pair = (first, second)
        freqs += gen(pair, 1)

    print(freqs)
    return max(freqs.values()) - min(freqs.values())

def main():
    input = parse('data/day14.txt')
    result = solve(input, 40)
    print(result)

if __name__ == '__main__':
    main()
