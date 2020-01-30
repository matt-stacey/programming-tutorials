# https://pythonprogramming.net/pygame-python-3-part-1-intro/

import pygame
import time
import os

pygame.init()

res = 'racey_resources'
display_width = 800
display_height = 600

gameDisplay = pygame.display.set_mode((display_width, display_height))
pygame.display.set_caption('A Bit Racey')

black = (0, 0, 0)
white = (255, 255, 255)
red = (255, 0, 0)

carImg = pygame.image.load(os.path.join(res, 'racecar.png'))
car_width = 73

log = open(os.path.join(res, 'game.log'), 'w')

clock = pygame.time.Clock()

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
    
def car(x, y):
    gameDisplay.blit(carImg, (x, y))

def exit_game():
    pygame.quit()
    log.close()
    quit()
        
def game_loop():
    x = display_width * 0.45
    x_change = {}
    y = display_height * 0.8
    
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
        
        gameDisplay.fill(white)
        car(x, y)
        
        if x < 0 or display_width < x + car_width:
            crash()
        
        pygame.display.update()
        clock.tick(60)
    
game_loop()
exit_game()