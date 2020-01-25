import pyglet
import random
from . import resources, asteroid, util


def asteroids(num_asteroids, player_position, batch=None):
    asteroids = []
    for i in range(num_asteroids):
        asteroid_x, asteroid_y = player_position
        while util.distance((asteroid_x, asteroid_y), player_position) < 250:
            asteroid_x = random.randint(0, resources.width)
            asteroid_y = random.randint(0, resources.height)
        new_asteroid = asteroid.Asteroid(x=asteroid_x, y=asteroid_y, batch=batch)
        asteroids.append(new_asteroid)
    return asteroids
