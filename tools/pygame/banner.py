import sys, pygame

with open('banner.txt', 'r') as f:
    banner = f.read()

pygame.init()

size = width, height = 640, 480
black = 0, 0, 0
white = 255, 255, 255

screen = pygame.display.set_mode(size)

font = pygame.font.Font('VeraMono.ttf', 15)

def draw_text(text, x, y, color):
    label = font.render(text, True, color)
    screen.blit(label, (x, y))

def draw_banner():
    y = 0
    for line in banner.split('\n'):
        draw_text(line, 5, y, white)
        y += font.get_height()

def draw_options(options):
    y = 400
    for (number, option) in options:
        selection = "[%s]" % number
        draw_text(selection, 10, y, (200, 200, 200))
        draw_text(option, 12 + font.size(selection)[0], y, (100, 100, 100))
        y += font.get_height()

while True:
    for event in pygame.event.get():
        if event.type == pygame.QUIT: sys.exit()

    screen.fill(black)
    draw_banner()
    draw_options([
        ("X", "Doesn't work"),
        ("Q", "Quit")
    ])
    pygame.display.flip()
