def SayHi(name = "User", age = 0):
    print(f"Hello, {name}!")
    print(f"You are {age} years old!")
    
SayHi()
SayHi("Matt", 33)
name = "Matt"
age = 33
SayHi(name, age)
#SayHi(input("What is your name? "), input("What is your age? "))

def cube(num = 0):
    return num ** 3

result = cube(4)
print(result)