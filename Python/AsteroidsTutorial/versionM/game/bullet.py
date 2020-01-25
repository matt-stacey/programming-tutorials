import pyglet
from . import physicalobject, resources


class Bullet(physicalobject.PhysicalObject):

    def __init__(self, *args, **kwargs):
        super(Bullet, self).__init__(img=resources.bullet_image, *args, **kwargs)

        # bullet constants
        self.bullet_speed = 700.0
        self.bullet_time = min(resources.height, resources.width) / self.bullet_speed  # to prevent wrap-around
        self.is_bullet = True

        # kill after lifetime
        pyglet.clock.schedule_once(self.die, self.bullet_time)

    def die(self, dt):
        self.dead = True
