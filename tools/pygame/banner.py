import sys, pygame

with open('banner.txt', 'r') as f:
    banner = f.read()

pygame.init()

size = width, height = 640, 480
black = 0, 0, 0
white = 255, 255, 255

screen = pygame.display.set_mode(size)

x_options = ["Doesn't work", "It REALLY doesn't work", "Stop selecting this option", "OK, last chance"]
x_current_option = 0

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
        if event.type == pygame.QUIT or \
          (event.type == pygame.KEYDOWN and event.key == pygame.K_q):
          sys.exit()
        elif event.type == pygame.KEYDOWN and event.key == pygame.K_x:
          x_current_option += 1

    screen.fill(black)
    draw_banner()
    if x_current_option < len(x_options): 
        options = [("X", x_options[x_current_option]), ("Q", "Quit")]
    else:
        options = [("Q", "I told you")]
    draw_options(options)
    pygame.display.flip()
