# first calculator

num1 = float(input("Enter a number: "))
num2 = float(input("Enter another number: "))

result = num1 + num2
print(result)


# better calculator

num1 = float(input("Enter a number: "))
oper = None
while not(oper == "+" or oper == "-" or oper == "*" or oper == "/"):
    oper = str(input("Enter an operator (+ - * /): "))
num2 = float(input("Enter another number: "))

if oper == "+":
    result = num1 + num2
elif oper == "-":
    result = num1 - num2
elif oper == "*":
    result = num1 * num2
elif oper == "/":
    result = num1 / num2
else:
    print("Invalid operator!")
    result = None

print(result)