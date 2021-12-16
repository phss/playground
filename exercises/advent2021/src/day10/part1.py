def parse(filename: str):
    with open(filename) as file:
        return file.read().split('\n')

def solve(input: list[str]) -> int:
    matchingRules = {
        '(': ')',
        '[': ']',
        '{': '}',
        '<': '>'
    }
    points = {
        ')': 3,
        ']': 57,
        '}': 1197,
        '>': 25137
    }
    score = 0

    for chunk in input:
        brackets = []
        for _, newBracket in enumerate(chunk):
            if newBracket in matchingRules:
                brackets.append(newBracket)
            else:
                openBracket = brackets.pop()
                if matchingRules[openBracket] != newBracket:
                    score += points[newBracket]
                    break
        
    return score

def main():
    input = parse('data/day10.txt')
    result = solve(input)
    print(result)

if __name__ == '__main__':
    main()