import sys, pygame

with open('banner.txt', 'r') as f:
    banner = f.read()

pygame.init()

size = width, height = 640, 480
black = 0, 0, 0
white = 255, 255, 255

screen = pygame.display.set_mode(size)

font = pygame.font.Font('VeraMono.ttf', 15)

def draw_text(text, x, y):
    label = font.render(text, True, white)
    screen.blit(label, (x, y))

def draw_banner():
    y = 0
    for line in banner.split('\n'):
        draw_text(line, 5, y)
        y += font.get_height()


while True:
    for event in pygame.event.get():
        if event.type == pygame.QUIT: sys.exit()

    screen.fill(black)
    draw_banner()
    pygame.display.flip()
