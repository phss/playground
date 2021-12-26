from functools import cache
from typing import Counter

# input = (4, 8)
input = (7, 3)

allDicePermutations = []
for roll1 in range(1, 4):
    for roll2 in range(1, 4):
        for roll3 in range(1, 4):
            allDicePermutations.append(roll1 + roll2 + roll3)
uniqueDicePerms = Counter(allDicePermutations)

def solve(input) -> int:
    @cache
    def calculate(positions, scores, player):
        if max(scores) >= 21:
            return [1, 0] if scores[0] > scores[1] else [0, 1] 

        wins = [0, 0]
        for roll, times in uniqueDicePerms.items():
            branchPositions, branchScores = list(positions), list(scores)
            branchPositions[player] += roll
            branchPositions[player] = (branchPositions[player] -1) % 10 + 1
            branchScores[player] += branchPositions[player]

            subWins = calculate(tuple(branchPositions), tuple(branchScores), (player+1)%2)
            wins[0] += subWins[0] * times
            wins[1] += subWins[1] * times
            
        return wins

    print(calculate(input, (0, 0), 0))
    return 0

def main():
    result = solve(input)
    print(result)

if __name__ == '__main__':
    main()