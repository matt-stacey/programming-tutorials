import math, pyglet
from pyglet.window import key
from . import physicalobject, resources, bullet


class Player(physicalobject.PhysicalObject):

    def __init__(self, *args, **kwargs):
        super(Player, self).__init__(img=resources.player_image, *args, **kwargs)

        # ship constants
        self.thrust = 300.0
        self.rotate_speed = 200.0
        self.reacts_to_bullets = False

        # pyglet can handle keypresses
        self.key_handler = key.KeyStateHandler()
        self.event_handlers = [self, self.key_handler]

        # add engine flames
        self.engine_sprite = pyglet.sprite.Sprite(img=resources.engine_image, *args, **kwargs)
        self.engine_sprite.visible = False

    def update(self, dt):
        super(Player, self).update(dt)

        if self.key_handler[key.LEFT]:
            self.rotation -= self.rotate_speed * dt  # degrees, clockwise positive
        if self.key_handler[key.RIGHT]:
            self.rotation += self.rotate_speed * dt

        if self.key_handler[key.UP]:
            angle_radians = -math.radians(self.rotation)  # negative, due to orientation of display and rotation
            force_x = math.cos(angle_radians) * self.thrust * dt
            force_y = math.sin(angle_radians) * self.thrust * dt
            self.velocity_x += force_x
            self.velocity_y += force_y

            # update engine flames
            self.engine_sprite.rotation = self.rotation
            self.engine_sprite.x = self.x
            self.engine_sprite.y = self.y
            self.engine_sprite.visible = True
        else:
            self.engine_sprite.visible = False

    def on_key_press(self, symbol, modifiers):
        if symbol == key.SPACE:
            self.fire()

    def fire(self):
        if not self.dead:
            angle_radians = -math.radians(self.rotation)

            # create a bullet just in front of the player
            ship_radius = self.image.width / 2
            bullet_x = self.x + math.cos(angle_radians) * ship_radius
            bullet_y = self.y + math.sin(angle_radians) * ship_radius
            new_bullet = bullet.Bullet(x=bullet_x, y=bullet_y, batch=self.batch)

            # set the bullet's speed
            # modified here from example, bullet speed in Bullet class rather than here
            new_bullet.velocity_x = (self.velocity_x + math.cos(angle_radians) * new_bullet.bullet_speed)
            new_bullet.velocity_y = (self.velocity_y + math.sin(angle_radians) * new_bullet.bullet_speed)

            self.new_objects.append(new_bullet)

            resources.bullet_sound.play()

    def delete(self):
        # overwrites the automatically called delete function
        self.engine_sprite.delete()
        super(Player, self).delete()
