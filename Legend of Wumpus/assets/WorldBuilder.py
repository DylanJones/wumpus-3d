#!/usr/bin/env python3
import sys; version = int(sys.version.split(' ')[0].split('.')[0])
if version == 2:
    from Tkinter import *
elif version == 3:
    from tkinter import *
else:
    raise Exception("THIS ISNT PYTHON!")
import this
from PIL import Image, ImageTk
from tkinter import filedialog
#Setup
w, h = 512, 336
root = Tk()
canvas = Canvas(root, width = w, height = h, background = 'white')
canvas.pack()
toolLabel = Label(root, text = "None")
toolLabel.pack()
click = None
move = None
bgimg = None #PIL image
bg_canvas = None #Canvas image

def mouseClick(evt):
    if evt.x <= w and evt.y <= h:
        click(evt)
def mouseMove(evt):
    if evt.x <= w and evt.y <= h:
        move(evt)
canvas.bind("<Button-1>", mouseClick)
canvas.bind("<Motion>", mouseMove)
walls = [] #tkid of walls on canvas
enemies = [] #tuple of enemies on canvas

def save(evt = None):
    with filedialog.asksaveasfile() as file:
        file.write("metadata:\n")
        tmp = bgimg.filename.split('/')
        fname = tmp[len(tmp) - 1]
        file.write("image " + fname)
        file.write("walls:\n")
        for wall in walls:
            coords = canvas.coords(wall)
            x = int(coords[0] if coords[0] < coords[2] else coords[2])
            y = int(coords[1] if coords[1] < coords[3] else coords[3])
            if coords[0] == coords[2]:
                #Vertical
                length = int(abs(coords[1] - coords[3]))
                type = "vertical"
            else:
                #Horizontal
                length = int(abs(coords[0] - coords[2]))
                type = "horizontal"
            file.write(str(x) + " " + str(y) + " " + str(length) + " " + type + "\n")
        #More stuff
def undo(evt = None):
    canvas.delete(walls[len(walls)-1])
    walls.pop()

def set_background(evt = None):
    global bgimg, bg_canvas, tkimg
    bgimg = Image.open(filedialog.askopenfile().name)
    tkimg = ImageTk.PhotoImage(bgimg)
    bg_canvas = canvas.create_image(w / 2, h / 2, image = tkimg)

root.bind('<Shift-O>', set_background)
root.bind('<Shift-S>', save)
root.bind('<Shift-Z>', undo)
######################Tool functions###########################
###WALL###
dragging = False
def setWall():
    global click, move
    click = wallClick
    move = wallMove
    toolLabel.config(text="Wall")

def wallClick(evt):
    global dragging, startCoord, wallLine
    if not dragging: #starting to create line
        dragging = True
        startCoord = (evt.x, evt.y)
        wallLine = canvas.create_line(evt.x, evt.y, evt.x, evt.y, fill = 'black', width = 2)
    else: #we're finishing
        walls.append(wallLine)
        dragging = False

def wallMove(evt):
    global dragging, wallLine
    if dragging:
        if abs(startCoord[0] - evt.x) > abs(startCoord[1] - evt.y):
            canvas.coords(wallLine, (startCoord[0], startCoord[1], evt.x, startCoord[1]))
        else:
            canvas.coords(wallLine, (startCoord[0], startCoord[1], startCoord[0], evt.y))

###GREMLIN###
def gremlinClick(evt):
    global dragging, startCoord, gremlinRect, gremlinImage
    if not dragging: #starting to create line
        dragging = True
        startCoord = (evt.x, evt.y)
        gremlinRect = canvas.create_rectangle((evt.x, evt.y, evt.x, evt.y), fill = None)
        gremlinImage = ""
    else: #we're finishing
        enemies.append(('gremlin', gremlinImage, gremlinRect))
        dragging = False

def gremlinMove(evt):
    if dragging:
        canvas.coords(gremlinRect, startCoord[0], startCoord[1], evt.x, evt.y)
    pass
#Tool buttons
wall_button = Button(root, text = "Wall", command = setWall)
wall_button.pack()

##########################ENTITY SELECT DIALOG###################
def entity_select():
    entity_dialog = Tk()
    def setGremlin():
        global click, move
        click = gremlinClick
        move = gremlinMove
        toolLabel.config(text = "Gremlin")
    gremlinButton = Button(entity_dialog, text = "Gremlin", command = setGremlin)
    quitButton = Button(entity_dialog, text = "Close", command=entity_dialog.destroy)
    gremlinButton.pack()
    quitButton.pack()
    entity_dialog.mainloop()

entity_button = Button(root, text = "Entities...", command = entity_select)
entity_button.pack()
root.mainloop()
