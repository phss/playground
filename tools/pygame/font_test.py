import sys, pygame
pygame.init()

size = width, height = 320, 240
black = 0, 0, 0
white = 255, 255, 255

screen = pygame.display.set_mode(size)

font = pygame.font.Font(None, 20)

def draw_text(text, x, y):
    label = font.render(text, True, white)
    screen.blit(label, (x, y))

while True:
    for event in pygame.event.get():
        if event.type == pygame.QUIT: sys.exit()

    screen.fill(black)
    draw_text("Hello there", 100, 100)
    pygame.display.flip()
