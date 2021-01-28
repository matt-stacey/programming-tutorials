import pyglet


class MyWindow(pyglet.window.Window):
    def __init__(self):
        super(MyWindow, self).__init__(1280, 720,
                                       resizable=True,
                                       visible=False,
                                       caption='Window caption',
                                       vsync=False,
                                       screen=pyglet.canvas.get_display().get_screens()[0],
                                       )

        self.set_minimum_size(320, 200)
        self.set_maximum_size(1440, 800)
        #self.set_size(1280, 720)

        x, y = self.get_location()
        self.set_location(x - 20, y - 20)

        self.set_caption('New caption')
        self.label = pyglet.text.Label('Hello, world!')

        self.set_visible()

    def on_resize(self, width, height):
        print('The window was resized to {}x{}'.format(width, height))
        self.label.draw()

    def on_draw(self):
        self.clear()
        self.label.draw()

    """
    super annoying...change full-/not full-screen on every mouse click
    def on_mouse_press(x, y, button, modifiers):
        window.set_fullscreen(not window.fullscreen)
    """


if __name__ == '__main__':
    window = MyWindow()
    pyglet.app.run()
