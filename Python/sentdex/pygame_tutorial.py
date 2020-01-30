# https://pythonprogramming.net/pygame-python-3-part-1-intro/
# with some modifications

import pygame
import time
import random
import os

pygame.init()

res = 'racey_resources'
display_width = 1080
display_height = 1200

gameDisplay = pygame.display.set_mode((display_width, display_height))
pygame.display.set_caption('A Bit Racey')

high = 0

black = (0, 0, 0)
white = (255, 255, 255)
red = (255, 0, 0)

carImg = pygame.image.load(os.path.join(res, 'racecar.png'))
car_width = 73
car_height = 73  # just a guess for now

log = open(os.path.join(res, 'game.log'), 'w')

clock = pygame.time.Clock()

def things_dodged(dodged, speed, width):
    font = pygame.font.SysFont(None, 25)
    words = ('Dodged: ', 'Speed: ', 'Width: ')
    nums = (dodged, speed, width)
    for num, wn in enumerate(zip(words, nums)):
        message = '{}{}'.format(wn[0], wn[1])
        text = font.render(message, True, black)
        gameDisplay.blit(text,(0, 25*num))

def high_score(score):
    font = pygame.font.SysFont(None, 25)
    text = font.render('High score: {}'.format(score), True, black)
    gameDisplay.blit(text,(0, 100))

def text_objects(text, font):
    textSurface = font.render(text, True, black)
    return textSurface, textSurface.get_rect()

def message_display(text):
    largeText = pygame.font.Font(os.path.join(res,'FreeSansBold.ttf'),115)
    TextSurf, TextRect = text_objects(text, largeText)
    TextRect.center = ((display_width/2),(display_height/2))
    gameDisplay.blit(TextSurf, TextRect)

    pygame.display.update()
    time.sleep(2)
    
def crash():
    message_display('You Crashed')
    game_loop()
    
def things(thingx, thingy, thingw, thingh, color):
    pygame.draw.rect(gameDisplay, color, [thingx, thingy, thingw, thingh])

def car(x, y):
    gameDisplay.blit(carImg, (x, y))

def exit_game():
    pygame.quit()
    log.close()
    quit()
        
def game_loop():
    x = display_width * 0.45
    y = display_height * 0.8
    x_change = {}
    
    thing_width = 100
    thing_height = 100
    thing_startx = random.randrange(0, display_width - thing_width)
    thing_starty = -600
    thing_speed = 7
    thing_color = black
    
    dodged = 0
    
    gameExit = False
    while not gameExit:
        for event in pygame.event.get():
            if event.type == pygame.QUIT:
                exit_game()
    
            if event.type == pygame.FINGERDOWN:
                if event.x < 0.5:
                    x_change[event.fingerId] = -5
                else:
                    x_change[event.fingerId] = 5
            if event.type == pygame.KEYDOWN:
                if event.key == pygame.K_LEFT:
                    x_change[event.key] = -5
                if event.key == pygame.K_RIGHT:
                    x_change[event.key] = 5

            if event.type == pygame.FINGERUP:
                x_change.pop(event.fingerId, None)
            if event.type == pygame.KEYUP:
                if event.key == pygame.K_LEFT or event.key == pygame.K_RIGHT:
                    x_change.pop(event.key, None)
                    
        for xchg in x_change.values():
            x += xchg
        thing_starty += thing_speed
        
        gameDisplay.fill(white)
        # things(thingx, thingy, thingw, thingh, color)
        things(thing_startx, thing_starty, thing_width, thing_height, thing_color)
        car(x, y)
        things_dodged(dodged, thing_speed, thing_width)
        high_score(globals()['high'])
        
        if x < 0 or display_width < x + car_width:
            crash()
        
        if (thing_startx <= x + car_width and x <= thing_startx + thing_width):  # lateral overlap
            if (y <= thing_starty + thing_height and thing_starty <= y + car_height):  # vertical overlap
                crash()
        
        if thing_starty > display_height:
            thing_starty = 0 - thing_height
            dodged += 1
            if dodged > globals()['high']:
                globals()['high'] = dodged
            thing_speed += 1
            thing_width += (dodged * 1.2)
            thing_color = (random.randint(0, 127),
                           random.randint(0, 127),
                           random.randint(0, 127))
            thing_startx = random.randrange(0,display_width - thing_width)
        
        pygame.display.update()
        clock.tick(60)
    
game_loop()
exit_game()