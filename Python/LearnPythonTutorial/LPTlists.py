# lists

friends = ["Kevin", "Karen", "Jim", "Oscar", "Toby"]
print(friends)
print(friends[1])
print(friends[-1])
print(friends[1:])
print(friends[1:3])
for i in range(3):
    print(friends[i])
friends[1] = "Mike"
for fr in friends:
    print(fr)


# list functions

lucky_numbers = [4, 8, 15, 16, 23, 42]
friends = ["Kevin", "Karen", "Jim", "Oscar", "Toby", "Kelly"]
friends2 = friends.copy()
friends.extend(lucky_numbers)
friends.append("Creed")
friends.insert(1, "Kelly")
friends.remove("Jim")

popped = friends.pop()
print(friends)
print(popped)
print(friends.index("Kevin"))
print(friends.count("Kelly"))


# tuples
# unpackable, immutable lists

coordinates = (4, 5)
print(coordinates[1])

x, y = coordinates
print(f"Coordinates: ({x}, {y})")

# dictionaries

months = {
    "Jan" : "January", 
    "Feb" : "February", 
    "Mar" : "March", 
    "Apr" : "April", 
    "May" : "May", 
    "Jun" : "June", 
    "Jul" : "July", 
    "Aug" : "August", 
    "Sep" : "September", 
    "Oct" : "October", 
    "Nov" : "November", 
    "Dec" : "December"
}

print(months["Nov"])
print(months.get("Luv", "Not a valid key"))


# 2D lists

number_grid = [
    [1, 2, 3],
    [4, 5, 6],
    [7, 8, 9],
    [0]
]
print(number_grid)
print(number_grid[1])
print(number_grid[1][1])