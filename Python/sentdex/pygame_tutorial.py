# https://pythonprogramming.net/pygame-python-3-part-1-intro/
# with some modifications

import pygame
import time
import random
import os

pygame.init()

res = 'racey_resources'
log = open(os.path.join(res, 'game.log'), 'w')

display_width = 1080
display_height = 1200

gameDisplay = pygame.display.set_mode((display_width, display_height))
pygame.display.set_caption('A Bit Racey')

high = 0

black = (0, 0, 0)
white = (255, 255, 255)
red = (200,0,0)
green = (0,200,0)
bright_red = (255,0,0)
bright_green = (0,255,0)

freeSans = os.path.join(res,'FreeSansBold.ttf')

carImg = pygame.image.load(os.path.join(res, 'racecar.png'))  # https://pythonprogramming.net/static/images/pygame/racecar.png
car_width = 73
car_height = 73  # just a guess for now
car_spd = 10

#pygame.mixer.init()
crash_sound = pygame.mixer.Sound(os.path.join(res, 'Crash-Cymbal-4.wav'))  # https://freewavesamples.com/crash-cymbal-4
pygame.mixer.music.load(os.path.join(res, 'bensound-allthat.mp3'))  # https://www.bensound.com/bensound-music/bensound-allthat.mp3
pygame.mixer.music.set_volume(.15)
pygame.mixer.music.play(-1)


clock = pygame.time.Clock()

def things_dodged(dodged, speed, width):
    font = pygame.font.SysFont(None, 25)
    words = ('Dodged: ', 'Speed: ', 'Width: ')
    nums = (dodged, speed, width)
    for num, wn in enumerate(zip(words, nums)):
        message = '{}{}'.format(wn[0], round(wn[1], 1))
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
    largeText = pygame.font.Font(freeSans,115)
    TextSurf, TextRect = text_objects(text, largeText)
    TextRect.center = ((display_width/2),(display_height/2))
    gameDisplay.blit(TextSurf, TextRect)

    pygame.display.update()
    time.sleep(2)

def button(msg,x,y,w,h,ic,ac,s,func=None):
    mouse = pygame.mouse.get_pos()
    click = pygame.mouse.get_pressed()
    
    if x+w > mouse[0] > x and y+h > mouse[1] > y:
        pygame.draw.rect(gameDisplay, ac,(x,y,w,h))
        if click[0] == 1 and func:
            func()
    else:
        pygame.draw.rect(gameDisplay, ic,(x,y,w,h))

    smallText = pygame.font.Font(freeSans,s)
    textSurf, textRect = text_objects(msg, smallText)
    textRect.center = ( (x+(w/2)), (y+(h/2)) )
    gameDisplay.blit(textSurf, textRect)

def crash():
    crash_sound.play()
    message_display('You Crashed')
    game_intro()

def game_intro():

    btn_w = 180
    btn_h = 90
    btn_L = display_width / 2 - btn_w * 2
    btn_R = display_width / 2 + btn_w
    btn_up = display_height * 0.8
    
    intro = True

    while intro:
        for event in pygame.event.get():
            print(event)
            if event.type == pygame.QUIT:
                pygame.quit()
                quit()
                
        gameDisplay.fill(white)
        largeText = pygame.font.Font(freeSans,115)
        TextSurf, TextRect = text_objects("A bit Racey", largeText)
        TextRect.center = ((display_width/2),(display_height/2))
        gameDisplay.blit(TextSurf, TextRect)
        
        #mouse = pygame.mouse.get_pos()

        #print(mouse)
        #button(msg,x,y,w,h,ic,ac,s,func)
        button('GO!', btn_L, btn_up, btn_w, btn_h, green, bright_green, 50, game_loop)
        button('STOP!', btn_R, btn_up, btn_w, btn_h, red, bright_red, 50, exit_game)
        
        pygame.display.update()
        clock.tick(15)

def things(thingx, thingy, thingw, thingh, color):
    pygame.draw.rect(gameDisplay, color, [thingx, thingy, thingw, thingh])

def car(x, y):
    gameDisplay.blit(carImg, (x, y))

def exit_game():
    fade_out = 1
    pygame.mixer.music.fadeout(fade_out * 1000)
    log.close()
    time.sleep(fade_out)
    pygame.quit()
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
                    x_change[event.fingerId] = -car_spd
                else:
                    x_change[event.fingerId] = car_spd
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
            thing_startx = random.randrange(0,int(display_width - thing_width))
        
        pygame.display.update()
        clock.tick(60)

game_intro()
game_loop()
exit_game()