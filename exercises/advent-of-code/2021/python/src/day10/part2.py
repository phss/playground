def parse(filename: str):
    with open(filename) as file:
        return file.read().split('\n')

def incompleteBrackets(chunk: str) -> list[str]:
    matchingRules = {
        '(': ')',
        '[': ']',
        '{': '}',
        '<': '>'
    }
    brackets = []
    for _, newBracket in enumerate(chunk):
        if newBracket in matchingRules:
            brackets.append(newBracket)
        else:
            openBracket = brackets.pop()
            if matchingRules[openBracket] != newBracket:
                return []
    return brackets


def solve(input: list[str]) -> int:
    points = {
        '(': 1,
        '[': 2,
        '{': 3,
        '<': 4
    }

    scores = []
    for chunk in input:
        brackets = incompleteBrackets(chunk)

        currentScore = 0
        while len(brackets) > 0:
            currentScore = currentScore*5 + points[brackets.pop()]
        
        if currentScore > 0:
            scores.append(currentScore)
    scores.sort()
        
    return scores[len(scores) // 2]

def main():
    input = parse('data/day10.txt')
    result = solve(input)
    print(result)

if __name__ == '__main__':
    main()