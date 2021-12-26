
# input = (4, 8)
input = (7, 3)

class Dice:
    def __init__(self) -> None:
        self.rolls = 0
        self.dice = 0

    def roll(self):
        self.rolls += 1
        self.dice += 1
        if self.dice > 100:
            self.dice = 1
        return self.dice

def solve(input) -> int:
    positions = list(input)
    scores = [0, 0]
    dice = Dice()

    while max(scores) < 1000:
        for player in range(2):
            move = dice.roll() + dice.roll() + dice.roll()
            positions[player] += move
            positions[player] = (positions[player] -1) % 10 + 1
            scores[player] += positions[player]
            # print(player, move, positions[player], scores[player])
            if max(scores) >= 1000:
                break
# 
    print(dice.rolls)
    print(scores)
    return dice.rolls * min(scores)

def main():
    result = solve(input)
    print(result)

if __name__ == '__main__':
    main()