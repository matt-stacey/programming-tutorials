try:
    number = int(input("Enter a number: "))
except ZeroDivisionError as err:
    print(f"Divide by zero: {err}")
except ValueError as err:
    print(f"Invalid input: {err}")
except:
    print("Unknown error")
else:
    print(number)
finally:
    print("Program complete")