import pyglet

# window size
width = 1200
height = 600


def center_image(image):
    # reset image anchor to its center
    image.anchor_x = image.width // 2
    image.anchor_y = image.width // 2


# tell pyglet where to find the pngs, etc
pyglet.resource.path = ['../resources']  # same as cd commands
pyglet.resource.reindex()

# load and center images
player_image = pyglet.resource.image('player.png')
center_image(player_image)

bullet_image = pyglet.resource.image('bullet.png')
center_image(bullet_image)

asteroid_image = pyglet.resource.image('asteroid.png')
center_image(asteroid_image)

engine_image = pyglet.resource.image('engine_flame.png')
engine_image.anchor_x = engine_image.width * 1.5
engine_image.anchor_y = engine_image.height / 2

bullet_sound = pyglet.resource.media('bullet.wav', streaming=False)