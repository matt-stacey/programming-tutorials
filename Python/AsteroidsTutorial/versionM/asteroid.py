import pyglet
from game import resources, load, player, asteroid

# window size
width = resources.width
height = resources.height

# start from scratch
current_level = 0
player_ship = None
game_objects = []
event_stack_size = 0

# set up a window
game_window = pyglet.window.Window(width, height)  # create the game window

# set up drawing batches
main_batch = pyglet.graphics.Batch()

# set up text in the window
level_label = pyglet.text.Label(text='Level: {0}'.format(current_level), x=10, y=height-25, batch=main_batch)
game_label = pyglet.text.Label(text='Rogue Asteroids', x=width//2, y=height-25, anchor_x='center',
                               batch=main_batch)

# start text offscreen
game_over_label = pyglet.text.Label(text='GAME OVER', x=width//2, y=-200, anchor_x='center', batch=main_batch)


def init():
    global current_level

    current_level = 0
    level_label.text = 'Level: {0}'.format(current_level)

    reset_level()


def reset_level():
    global player_ship, game_objects, event_stack_size

    # clear the event handler stack
    while event_stack_size > 0:
        game_window.pop_handlers()
        event_stack_size -= 1

    # initialize the player ship if it doesn't already exist in the level
    if player_ship is None:
        player_ship = player.Player(x=width // 2, y=height // 2, batch=main_batch)

    # adding asteroids makes the game less boring
    asteroids = load.asteroids(current_level, player_ship.position, main_batch)

    if not game_objects:
        game_objects = [player_ship]
    game_objects = game_objects + asteroids  # keeps bullets flying between levels

    # add event handlers
    for obj in game_objects:
        for handler in obj.event_handlers:
            game_window.push_handlers(handler)  # tells pyglet who responds to events
            event_stack_size += 1

    level_label.text = 'Level: {0}'.format(current_level)


@game_window.event
def on_draw():
    # draw things
    game_window.clear()
    main_batch.draw()  # replaces all individual draw functions


def update(dt):
    global current_level

    player_dead = False
    victory = False

    # collision detection and handling
    for i in range(len(game_objects)):
        for j in range(i+1, len(game_objects)):
            if not game_objects[i].dead and not game_objects[j].dead:
                if game_objects[i].collides_with(game_objects[j]):
                    game_objects[i].handle_collision_with(game_objects[j])
                    game_objects[j].handle_collision_with(game_objects[i])

    # add objects
    to_add = []

    asteroids_remaining = 0

    for obj in game_objects:
        obj.update(dt)
        to_add.extend(obj.new_objects)  # add the new objects to the placeholder list
        obj.new_objects = []  # and then take them off the list

        if isinstance(obj, asteroid.Asteroid):
            asteroids_remaining += 1

    if asteroids_remaining == 0:
        victory = True

    for to_remove in [obj for obj in game_objects if obj.dead]:  # very pythonic list comprehension
        to_add.extend(obj.new_objects)  # dying object children added last
        to_remove.delete()
        game_objects.remove(to_remove)
        if to_remove == player_ship:
            player_dead = True

    game_objects.extend(to_add)

    if player_dead:
        game_over_label.y = height // 2
    elif victory:
        current_level += 1
        reset_level()


if __name__ == '__main__':  # if this is the running .py file
    init()  # initialization
    pyglet.clock.schedule_interval(update, 1 / 120.0)  # 120 Hz refresh rate
    pyglet.app.run()  # do yo thang
