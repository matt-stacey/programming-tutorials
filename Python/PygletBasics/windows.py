import pyglet


window = pyglet.window.Window(1280, 720,
                              resizable=True,
                              visible=False,
                              caption='Window caption',
                              vsync=False,
                              screen=pyglet.canvas.get_display().get_screens()[0],
                              )

@window.event
def on_resize(width, height):
    print('The window was resized to {}x{}'.format(width, height))

"""
super annoying...change full-/not full-screen on every mouse click
@window.event
def on_mouse_press(x, y, button, modifiers):
    window.set_fullscreen(not window.fullscreen)
"""

window.set_minimum_size(320, 200)
window.set_maximum_size(1440, 800)
window.set_size(1280, 720)

x, y = window.get_location()
window.set_location(x - 20, y - 20)

window.set_caption('New caption')

window.set_visible()

if __name__ == '__main__':
    pyglet.app.run()
