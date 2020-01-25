# if statements
from pandas._libs import index

is_male = True
is_tall = False

if is_male and is_tall:
    print("You are a male and tall")
elif is_male and not(is_tall):
    print("You are a short male")
elif not(is_male) and is_tall:
    print("You are a  tall female")
else:
    print("You are neither a male nor tall")

def max_num(num1, num2, num3):
    if num1 >= num2 and num1 >= num3:
        return num1
    elif num2 >= num1 and num2 >= num3:
        return num2
    else:
        return num3

print(max_num(300, 40, 5))


# while loops

i = 1

while i <= 10:
    print(i)
    i += 1
print("Done with loop")


# for loops

for letter in "Giraffe Academy":
    print(letter, end='')
print()
    
friends = ["Jim", "Karen", "Kevin"]
for friend in friends:
    print(friend)

for index in range(3, 10):
    print(index)

def raise_to_power(base, power):
    result = 1
    for i in range(power):
        result *= base
    return result

print(raise_to_power(2,3))
print(raise_to_power(5,4))


# nested loops

number_grid = [
    [1, 2, 3],
    [4, 5, 6],
    [7, 8, 9],
    [0]
]

for row in number_grid:
    for column in row:
        print(column)
