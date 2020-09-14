import pyglet
from pyglet.window import key, mouse

def text_window():
    window = pyglet.window.Window()
    label = pyglet.text.Label('Hello, world',
                              font_name='Times New Roman',
                              font_size=36,
                              x=window.width//2, y=window.height//2,
                              anchor_x='center', anchor_y='center')

    @window.event
    def on_draw():
        window.clear()
        label.draw()

    return window

def cat_window():
    window = pyglet.window.Window()
    image = pyglet.resource.image('kitten.png')

    @window.event
    def on_draw():
        window.clear()
        image.blit(0, 0)

    return window

def key_window():
    window = pyglet.window.Window()

    @window.event
    def on_key_press(symbol, modifiers):
        if symbol == key.A:
            print('The "A" key was pressed.')
        elif symbol == key.LEFT:
            print('The left arrow key was pressed.')
        elif symbol == key.ENTER:
            print('The enter key was pressed.')
        else:
            print('A key was pressed.')

    @window.event
    def on_mouse_press(x, y, button, modifiers):
        if button == mouse.LEFT:
            print('The left mouse button was pressed.')

    event_logger = pyglet.window.event.WindowEventLogger()
    window.push_handlers(event_logger)

    return window

def main():
    window1 = text_window()
    window2 = cat_window()
    window3 = key_window()

    pyglet.app.run()

if __name__ == '__main__':
    main()
