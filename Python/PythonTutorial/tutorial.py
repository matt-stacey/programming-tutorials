# Python Tutorial
# https://docs.python.org/3/tutorial/index.html


# Stuff I need to work on
# List comprehensions / nested list comprehensions
# Generator expressions

#----------------------------------------------------------------------------------------------------------------------------------------

# Section 1: Whetting Appetite
# has no code

#----------------------------------------------------------------------------------------------------------------------------------------

# Section 2: Using Interpreter
the_world_is_flat = True
if the_world_is_flat:
    print("Be careful not to fall off!") # prints

#----------------------------------------------------------------------------------------------------------------------------------------

# Section 3: Informal Intro
# this is the first comment
spam = 1  # and this is the second comment
          # ... and now a third!
text = "# This is not a comment because it's inside quotes."
width = 20
height = 5 * 9
print(width * height) # 900, remains int
print(4 * 3.75 - 1) # 14.0, converts to float
print('C:\some\name') # here \n means newline!
print(r'C:\some\name') # note the r before the quote
print("""\
Usage: thingy [OPTIONS]
     -h                        Display this usage message
     -H hostname               Hostname to connect to
""") # """ to span multiple lines
print(3 * 'un' + 'ium') # unununium
word = 'Python'
print(word[0]) # character in position 0, 'P'
print(word[5]) # character in position 5, 'n'
print(word[-2]) # second-last character
print(word[:2] + word[2:]) # combines to 'Python', s[:i] + s[i:] is always equal to s
print(word[4:42]) # knows where it ends; 'on'
print('J' + word[1:]) # 'Jython'
s = 'supercalifragilisticexpialidocious'
print(len(s)) # 34
squares = [1, 4, 9, 16, 25]
print(squares[-3:])
print(squares + [36, 49, 64, 81, 100])
cubes = [1, 8, 27, 65, 125]  # something's wrong here
cubes[3] = 64  # replace the wrong value
print(cubes) # [1, 8, 27, 64, 125]
cubes.append(216)  # add the cube of 6
cubes.append(7 ** 3)  # and the cube of 7
print(cubes) # [1, 8, 27, 64, 125, 216, 343]
letters = ['a', 'b', 'c', 'd', 'e', 'f', 'g']
letters[2:5] = ['C', 'D', 'E']
print(letters) # ['a', 'b', 'C', 'D', 'E', 'f', 'g']
letters[2:5] = []
print(letters) # ['a', 'b', 'f', 'g']
print(len(letters)) # 4
letters[:] = []
print(letters) # []
a = ['a', 'b', 'c']
n = [1, 2, 3]
x = [a, n]
print(x) # [['a', 'b', 'c'], [1, 2, 3]]
print(x[0]) # ['a', 'b', 'c']
print(x[0][1]) # 'b'
a, b = 0, 1
while a < 10: # print fibonacci from 0 to 10
    print(a, end = ", ")
    a, b = b, a+b
print()

#----------------------------------------------------------------------------------------------------------------------------------------

# Section 4: Flow Control and Functions
x = int(input("Please enter an integer: "))
if x < 0:
    x = 0
    print('Negative changed to zero')
elif x == 0:
    print('Zero')
elif x == 1:
    print('Single')
else:
    print('More')
words = ['cat', 'window', 'defenestrate']
for w in words:
    print(w, len(w))
for i in range(5):
    print(i)
print(range(5, 10)) # 5, 6, 7, 8, 9
print(range(0, 10, 3)) # 0, 3, 6, 9
print(range(-10, -100, -30)) # -10, -40, -70
a = ['Mary', 'had', 'a', 'little', 'lamb']
for i in range(len(a)):
    print(i, a[i])
print(range(10))
print(list(range(5)))
for n in range(2, 10):
    for x in range(2, n):
        if n % x == 0:
            print(n, 'equals', x, '*', n//x)
            break
    else:
        # loop fell through without finding a factor, aka break statement isnt triggered
        print(n, 'is a prime number')
for num in range(2, 10):
    if num % 2 == 0:
        print("Found an even number", num)
        continue
    print("Found a number", num)
    
# function definition
def fib(n):    # write Fibonacci series up to n
    """Print a Fibonacci series up to n."""
    a, b = 0, 1
    while a < n:
        print(a, end=' ')
        a, b = b, a+b
    print()
    
# Now call the function we just defined:
fib(2000)
print(fib) # <function fib at 0x0000018E7931C1E0>
f = fib
f(100)
fib(0)
print(fib(0)) # None

def fib2(n):  # return Fibonacci series up to n
    """Return a list containing the Fibonacci series up to n."""
    result = []
    a, b = 0, 1
    while a < n:
        result.append(a)    # see below
        a, b = b, a+b
    return result

f100 = fib2(100) # call it
print(f100) # write the result

def ask_ok(prompt, retries=4, reminder='Please try again!'):
    while True:
        ok = input(prompt)
        if ok in ('y', 'ye', 'yes'):
            return True
        if ok in ('n', 'no', 'nop', 'nope'):
            return False
        retries = retries - 1
        if retries < 0:
            raise ValueError('invalid user response')
        print(reminder)

print(ask_ok('Do you really want to quit? '))
print(ask_ok('OK to overwrite the file? ', 2))
print(ask_ok('OK to overwrite the file? ', 2, 'Come on, only yes or no!'))

i = 5
def f(arg=i):
    print(arg)
i = 6
f() # prints 5 because default argument only evaluated once, at function definition

def f2(a, L=[]):
    L.append(a) # appends to each item, L is a reference to a list
    return L

print(f2(1)) # [1]
print(f2(2)) # [1, 2]
print(f2(3)) # [1, 2, 3]

def f3(a, L=None):
    if L is None:
        L = []
    L.append(a) # appends a to L; if L not passed, returns [a]
    return L

print(f3(1)) # [1]
print(f3(2)) # [2]
print(f3(3)) # [3]
print(f3(3, [1, 2])) # [1, 2, 3]

def parrot(voltage, state='a stiff', action='voom', ptype='Norwegian Blue'): # keyword arguments, kwarg = value
    print("-- This parrot wouldn't", action, end=' ')
    print("if you put", voltage, "volts through it.")
    print("-- Lovely plumage, the", ptype)
    print("-- It's", state, "!")

# these are all valid
parrot(1000)                                          # 1 positional argument
parrot(voltage=1000)                                  # 1 keyword argument
parrot(voltage=1000000, action='VOOOOOM')             # 2 keyword arguments
parrot(action='VOOOOOM', voltage=1000000)             # 2 keyword arguments
parrot('a million', 'bereft of life', 'jump')         # 3 positional arguments
parrot('a thousand', state='pushing up the daisies')  # 1 positional, 1 keyword
# invalid would be: 
# parrot()                     # required argument missing
# parrot(voltage=5.0, 'dead')  # non-keyword argument after a keyword argument
# parrot(110, voltage=220)     # duplicate value for the same argument
# parrot(actor='John Cleese')  # unknown keyword argument

def cheeseshop(kind, *arguments, **keywords): # accepts a mandatory arg, then args and kwargs
    print("-- Do you have any", kind, "?")
    print("-- I'm sorry, we're all out of", kind)
    for arg in arguments:
        print(arg)
    print("-" * 40)
    for kw in keywords:
        print(kw, ":", keywords[kw])
        
cheeseshop("Limburger", "It's very runny, sir.", # arg, *args, *kwargs
           "It's really very, VERY runny, sir.",
           shopkeeper="Michael Palin",
           client="John Cleese",
           sketch="Cheese Shop Sketch")
cheeseshop("Limburger") # arg only

def concat(*args, sep="/"): # anything after *args is keyword-only
    return sep.join(args)

print(concat("earth", "mars", "venus")) # 'earth/mars/venus'
print(concat("earth", "mars", "venus", sep=".")) # 'earth.mars.venus'

print(list(range(3, 6))) # normal call with separate arguments, [3, 4, 5]
args = [3, 6]
print(list(range(*args))) # call with arguments unpacked from a list using the *, [3, 4, 5]

def parrot2(voltage, state='a stiff', action='voom'):
    print("-- This parrot wouldn't", action, end=' ')
    print("if you put", voltage, "volts through it.", end=' ')
    print("E's", state, "!")

d = {"voltage": "four million", "state": "bleedin' demised", "action": "VOOM"}
parrot2(**d) # unpack kwargs with **

def make_incrementor(n):
    return lambda x: x + n

f = make_incrementor(42)
print(f(0)) # 42
print(f(1)) # 43

pairs = [(1, 'one'), (2, 'two'), (3, 'three'), (4, 'four')]
pairs.sort(key=lambda pair: pair[1])
print(pairs) # [(4, 'four'), (1, 'one'), (3, 'three'), (2, 'two')]

#----------------------------------------------------------------------------------------------------------------------------------------

# Section 5: Data Structures
fruits = ['orange', 'apple', 'pear', 'banana', 'kiwi', 'apple', 'banana']
print(fruits.count('apple')) # 2
print(fruits.count('tangerine')) # 0
print(fruits.index('banana')) # 3, for 1st appearance
print(fruits.index('banana', 4))  # 6, Find next banana starting a position 4
fruits.reverse()
print(fruits) # ['banana', 'apple', 'kiwi', 'banana', 'pear', 'apple', 'orange']
fruits.append('grape')
print(fruits) # ['banana', 'apple', 'kiwi', 'banana', 'pear', 'apple', 'orange', 'grape']
fruits.sort() # here, sorts alphabetically
print(fruits) # ['apple', 'apple', 'banana', 'banana', 'grape', 'kiwi', 'orange', 'pear']
print(fruits.pop()) # 'pear'

stack = [3, 4, 5]
stack.append(6)
stack.append(7)
print(stack) # [3, 4, 5, 6, 7]
print(stack.pop()) # 7
print(stack) # [3, 4, 5, 6]
stack.pop() # 6
stack.pop() # 5
print(stack) # [3, 4]

squares = []
for x in range(10):
    squares.append(x**2)
print(squares) # [0, 1, 4, 9, 16, 25, 36, 49, 64, 81]
squares = list(map(lambda x: x**2, range(10)))
print(squares) # same thing
squares = [x**2 for x in range(10)]
print(squares) # same thing again

# list comprehensions
print([(x, y) for x in [1,2,3] for y in [3,1,4] if x != y]) # [(1, 3), (1, 4), (2, 3), (2, 1), (2, 4), (3, 1), (3, 4)]
combs = []
for x in [1,2,3]:
    for y in [3,1,4]:
        if x != y:
            combs.append((x, y))
print(combs) # same thing: [(1, 3), (1, 4), (2, 3), (2, 1), (2, 4), (3, 1), (3, 4)]

vec = [-4, -2, 0, 2, 4] 
print([x*2 for x in vec]) # create a new list with the values doubled, [-8, -4, 0, 4, 8]
print([x for x in vec if x >= 0]) # filter the list to exclude negative numbers, [0, 2, 4]
print([abs(x) for x in vec]) # apply a function to all the elements, [4, 2, 0, 2, 4]
freshfruit = ['  banana', '  loganberry ', 'passion fruit  ']
print([weapon.strip() for weapon in freshfruit]) # call a method on each element, ['banana', 'loganberry', 'passion fruit']
print([(x, x**2) for x in range(6)]) # create a list of 2-tuples like (number, square), [(0, 0), (1, 1), (2, 4), (3, 9), (4, 16), (5, 25)]
vec = [[1,2,3], [4,5,6], [7,8,9]]
print([num for elem in vec for num in elem]) # flatten a list using a listcomp with two 'for', [1, 2, 3, 4, 5, 6, 7, 8, 9]

# nested list comprehensions
matrix = [
    [1, 2, 3, 4],
    [5, 6, 7, 8],
    [9, 10, 11, 12],
    ]
print([[row[i] for row in matrix] for i in range(4)]) # transpose, [[1, 5, 9], [2, 6, 10], [3, 7, 11], [4, 8, 12]]
transposed = []
for i in range(4):
    transposed.append([row[i] for row in matrix])
print(transposed) # same thing, [[1, 5, 9], [2, 6, 10], [3, 7, 11], [4, 8, 12]]
transposed = []
for i in range(4):
    # the following 3 lines implement the nested listcomp
    transposed_row = []
    for row in matrix:
        transposed_row.append(row[i])
    transposed.append(transposed_row)
print(transposed) # same thing again, [[1, 5, 9], [2, 6, 10], [3, 7, 11], [4, 8, 12]]
print(list(zip(*matrix))) # tuples now, but transposed: [(1, 5, 9), (2, 6, 10), (3, 7, 11), (4, 8, 12)]

a = [-1, 1, 66.25, 333, 333, 1234.5]
del a[0]
print(a) # [1, 66.25, 333, 333, 1234.5]
del a[2:4]
print(a) # [1, 66.25, 1234.5]
del a[:]
print(a) # []
del a

# tuples
t = 12345, 54321, 'hello!'
print(t[0]) # 12345
print(t) # (12345, 54321, 'hello!')
v = ([1, 2, 3], [3, 2, 1])
print(v) # ([1, 2, 3], [3, 2, 1])
empty = ()
singleton = 'hello',    # <-- note trailing comma
len(empty) # 0
len(singleton) # 1
print(singleton) # ('hello',)
x, y, z = t
print(x) # 12345
print(y) # 54321
print(z) # 'hello!'

# sets
basket = {'apple', 'orange', 'apple', 'pear', 'orange', 'banana'}
# show that duplicates have been removed
print(basket) # {'orange', 'banana', 'pear', 'apple'}
# fast membership testing
print('orange' in basket) # True
print('crabgrass' in basket) # False
# Demonstrate set operations on unique letters from two words
a = set('abracadabra')
b = set('alacazam')
print(a) # unique letters in a, {'a', 'r', 'b', 'c', 'd'}
print(a - b) # letters in a but not in b, {'r', 'd', 'b'}
print(a | b) # letters in a or b or both, {'a', 'c', 'r', 'd', 'b', 'm', 'z', 'l'}
print(a & b) # letters in both a and b, {'a', 'c'}
print(a ^ b) # letters in a or b but not both, {'r', 'd', 'b', 'm', 'z', 'l'}
a = {x for x in 'abracadabra' if x not in 'abc'} # list comprehension on a set
print(a) # {'r', 'd'}

# dictionaries
tel = {'jack': 4098, 'sape': 4139}
tel['guido'] = 4127
print(tel) # {'jack': 4098, 'sape': 4139, 'guido': 4127}
print(tel['jack']) # 4098
del tel['sape']
tel['irv'] = 4127
print(tel) # {'jack': 4098, 'guido': 4127, 'irv': 4127}
print(list(tel)) # ['jack', 'guido', 'irv']
print(sorted(tel)) # ['guido', 'irv', 'jack']
print('guido' in tel) # True
print('jack' not in tel) # False
print(dict([('sape', 4139), ('guido', 4127), ('jack', 4098)])) # {'sape': 4139, 'guido': 4127, 'jack': 4098}
print(dict(sape=4139, guido=4127, jack=4098)) # {'sape': 4139, 'guido': 4127, 'jack': 4098}
print({x: x**2 for x in (2, 4, 6)}) # {2: 4, 4: 16, 6: 36}

# looping techniques
knights = {'gallahad': 'the pure', 'robin': 'the brave'}
for k, v in knights.items(): # unpacks
    print(k, v)
for i, v in enumerate(['tic', 'tac', 'toe']): # pulls indices and values
    print(i, v)
questions = ['name', 'quest', 'favorite color'] # use zip to compare 2 lists
answers = ['lancelot', 'the holy grail', 'blue']
for q, a in zip(questions, answers):
    print('What is your {0}?  It is {1}.'.format(q, a))
for i in reversed(range(1, 10, 2)): # count backwards
    print(i)
basket = ['apple', 'orange', 'apple', 'pear', 'orange', 'banana']
for f in sorted(set(basket)): # sort but leave original item unaltered
    print(f)
import math
raw_data = [56.2, float('NaN'), 51.7, 55.3, 52.5, float('NaN'), 47.8]
filtered_data = []
for value in raw_data:
    if not math.isnan(value):
        filtered_data.append(value)
print(filtered_data) # [56.2, 51.7, 55.3, 52.5, 47.8]

# more conditions
# in, not in: check if contained (or not)
# is, is not: check for exactness (or not)
# a < b == c: operators can be chained
# A and not B or C is equivalent to (A and (not B)) or C
string1, string2, string3 = '', 'Trondheim', 'Hammer Dance'
non_null = string1 or string2 or string3
print(non_null) # 'Trondheim'
# assignment cannot occur inside expressions
print((1, 2, 3)              < (1, 2, 4)) # all 7 will print True
print([1, 2, 3]              < [1, 2, 4])
print('ABC' < 'C' < 'Pascal' < 'Python')
print((1, 2, 3, 4)           < (1, 2, 4))
print((1, 2)                 < (1, 2, -1))
print((1, 2, 3)             == (1.0, 2.0, 3.0))
print((1, 2, ('aa', 'ab'))   < (1, 2, ('abc', 'a'), 4))

#----------------------------------------------------------------------------------------------------------------------------------------

# Section 6: Modules
import fibo # customarily at the beginning
fibo.fibo(1000) # 0 1 1 2 3 5 8 13 21 34 55 89 144 233 377 610 987
print(fibo.fibo2(100)) # [0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89]
print(fibo.__name__) # 'fibo'
fibo = fibo.fibo # assign a local name
fibo(500) # 0 1 1 2 3 5 8 13 21 34 55 89 144 233 377
del fibo
from fibo import fibo, fibo2 # directly imports without having to assign local name
fibo(500) # 0 1 1 2 3 5 8 13 21 34 55 89 144 233 377
import sys, builtins
print(dir(fibo))
print(dir(sys))
print(dir(builtins))

#----------------------------------------------------------------------------------------------------------------------------------------

# Section 7: Input and Output
year = 2016
event = 'Referendum'
print(f'Results of the {year} {event}') # 'Results of the 2016 Referendum', formaatted string literals
yes_votes = 42_572_654
no_votes = 43_132_495
percentage = yes_votes / (yes_votes + no_votes)
print('{:-9} YES votes  {:2.2%}'.format(yes_votes, percentage)) # ' 42572654 YES votes  49.67%'
s = 'Hello, world.'
print(str(s)) # 'Hello, world.'
print(repr(s)) # "'Hello, world.'"
print(str(1/7)) # '0.14285714285714285'
x = 10 * 3.25
y = 200 * 200
s = 'The value of x is ' + repr(x) + ', and y is ' + repr(y) + '...'
print(s) # The value of x is 32.5, and y is 40000...
# The repr() of a string adds string quotes and backslashes:
hello = 'hello, world\n'
hellos = repr(hello)
print(hellos) # 'hello, world\n'
# The argument to repr() may be any Python object:
print(repr((x, y, ('spam', 'eggs')))) # "(32.5, 40000, ('spam', 'eggs'))"
import math
print(f'The value of pi is approximately {math.pi:.3f}.') # The value of pi is approximately 3.142. // formatted string literal
table = {'Sjoerd': 4127, 'Jack': 4098, 'Dcab': 7678}
for name, phone in table.items():
    print(f'{name:10} ==> {phone:10d}') # use the : to make it a certain number of characters; helps with alignment
animals = 'eels'
print(f'My hovercraft is full of {animals}.') # My hovercraft is full of eels. '!a' applies ascii(), '!s' applies str()
print(f'My hovercraft is full of {animals!r}.') # My hovercraft is full of 'eels'. '!r' applies repr()
print('We are the {} who say "{}!"'.format('knights', 'Ni')) # We are the knights who say "Ni!" via str.format()
print('{0} and {1}'.format('spam', 'eggs')) # spam and eggs
print('{1} and {0}'.format('spam', 'eggs')) # eggs and spam
print('This {food} is {adjective}.'.format(food='spam', adjective='absolutely horrible')) # This spam is absolutely horrible.
print('The story of {0}, {1}, and {other}.'.format('Bill', 'Manfred', other='Georg')) # The story of Bill, Manfred, and Georg.
table = {'Sjoerd': 4127, 'Jack': 4098, 'Dcab': 8637678}
print('Jack: {0[Jack]:d}; Sjoerd: {0[Sjoerd]:d}; Dcab: {0[Dcab]:d}'.format(table)) # Jack: 4098; Sjoerd: 4127; Dcab: 8637678 via []
print('Jack: {Jack:d}; Sjoerd: {Sjoerd:d}; Dcab: {Dcab:d}'.format(**table)) # Jack: 4098; Sjoerd: 4127; Dcab: 8637678 via kwargs
for x in range(1, 11):
    print('{0:2d} {1:3d} {2:4d}'.format(x, x*x, x*x*x))
for x in range(1, 11):
    print(repr(x).rjust(2), repr(x*x).rjust(3), end=' ')
    # Note use of 'end' on previous line
    print(repr(x*x*x).rjust(4))
print('12'.zfill(5)) # '00012'
print('-3.14'.zfill(7)) # '-003.14'
print('3.14159265359'.zfill(5)) # '3.14159265359'
print('The value of pi is approximately %5.3f.' % math.pi) # The value of pi is approximately 3.142. old-school style
f = open('workfile', 'w') # 'r' read (assumed if omitted), 'w' writing (an existing file will be erased), 'a' appending, 'r+' both reading and writing
print(f.write('This is a test\n')) # prints # of chars written, 15
value = ('the answer', 42)
s = str(value)  # convert the tuple to string
print(f.write(s)) # 18
f.close()
with open('workfile') as f:
    for line in f:
        print(line, end='') # prints the above two lines
print('\n' + str(f.closed)) # True
f = open('workfile2', 'wb')
f.close()
f = open('workfile2', 'r+b')
print(f.write(b'0123456789abcdef')) # 16
print(f.seek(5)) # Go to the 6th byte in the file, returns char index
print(f.read(1)) # b'5'
print(f.seek(-3, 2))  # Go to the 3rd byte before the end
print(f.read(1)) # b'd'
f.close()

# Saving structured data with JSON (JavaScript Object Notation)
import json
print(json.dumps([1, 'simple', 'list'])) # '[1, "simple", "list"]'
x = [1, 'simple', 'list']
f = open('jsonfile', 'w')
f.close()
with open('jsonfile', 'r+') as f:
    json.dump(x, f)
    f.seek(0) # otherwise it tries to read from end of file
    x = json.load(f)
    print(x)

#----------------------------------------------------------------------------------------------------------------------------------------

# Section 8: Errors and Exceptions
while True: # repeats until a number is entered
    try:
        x = int(input("Please enter a number: "))
        break
    except ValueError:
        print("Oops!  That was no valid number.  Try again...")

class B(Exception):
    pass
class C(B):
    pass
class D(C):
    pass
for cls in [B, C, D]: # prints B, C, D
    try:
        raise cls()
    except D:
        print("D")
    except C:
        print("C")
    except B: # must be last, or it will print B, B, B as D is an extension of C is an extension of B
        print("B")

try:
    arg = 'myfile.txt'
    f = open(arg)
    s = f.readline()
    i = int(s.strip())
except OSError as err:
    print("OS error: {0}".format(err))
except ValueError:
    print("Could not convert data to an integer.")
except:
    print("Unexpected error:", sys.exc_info()[0])
    raise
else: # runs if no exception
    f.seek(0)
    print(arg, 'has', len(f.readlines()), 'lines')
    f.close()

try:
    raise Exception('spam', 'eggs') # force raising exception
    raise ValueError  # shorthand for 'raise ValueError()'
except Exception as inst:
    print(type(inst))    # the exception instance
    print(inst.args)     # arguments stored in .args
    print(inst)          # __str__ allows args to be printed directly, but may be overridden in exception subclasses
    x, y = inst.args     # unpack args
    print('x =', x)
    print('y =', y)

def this_fails():
    x = 1/0
try:
    this_fails() # exceptions handled in called functions by try clause
except ZeroDivisionError as err:
    print('Handling run-time error:', err) # Handling run-time error: division by zero
finally: # always runs
    print('Goodbye, world!')
    
# User-defined exceptions
class Error(Exception):
    """Base class for exceptions in this module."""
    pass
class InputError(Error):
    """Exception raised for errors in the input.
    Attributes:
    
        expression -- input expression in which the error occurred
        message -- explanation of the error
    """
    def __init__(self, expression, message):
        self.expression = expression
        self.message = message
class TransitionError(Error):
    """Raised when an operation attempts a state transition that's not
    allowed.
    
    Attributes:
        previous -- state at beginning of transition
        next -- attempted new state
        message -- explanation of why the specific transition is not allowed
    """
    def __init__(self, previous, next, message):
        self.previous = previous
        self.next = next
        self.message = message

def divide(x, y):
    try:
        result = x / y
    except ZeroDivisionError:
        print("division by zero!")
    else:
        print("result is", result)
    finally:
        print("executing finally clause")
divide(2, 1)
divide(2, 0)
# divide("2", "1") # returns TypeError

#----------------------------------------------------------------------------------------------------------------------------------------

# Section 9: Classes
def scope_test():
    def do_local():
        spam = "local spam"
    def do_nonlocal():
        nonlocal spam
        spam = "nonlocal spam"
    def do_global():
        global spam
        spam = "global spam"
    spam = "test spam"
    do_local()
    print("After local assignment:", spam) # test spam; changed in local scope of function only
    do_nonlocal()
    print("After nonlocal assignment:", spam) # nonlocal spam; change in nonlocal scope aka the module
    do_global()
    print("After global assignment:", spam) # nonlocal spam: global value of spam (not module-local) changed
scope_test()
print("In global scope:", spam) # global spam; changed in module function

class MyClass:
    """A simple example class"""
    i = 12345
    def f(self):
        return 'hello world'
    def __init__(self):
        self.data = []

x = MyClass

class Complex:
    def __init__(self, realpart, imagpart):
        self.r = realpart
        self.i = imagpart
        
x = Complex(3.0, -4.5)
x.counter = 1 # can create fields at runtime
while x.counter < 10:
    x.counter = x.counter * 2
print(x.counter) # 16
del x.counter
print(x.r, x.i) # (3.0, -4.5)

class Dog:

    kind = 'canine'         # class variable shared by all instances
    tricks = []             # mistaken use of a class variable

    def __init__(self, name):
        self.name = name    # instance variable unique to each instance
#        self.tricks = []    # creates a new empty list for each dog; should be here to get correct result
    def add_trick(self, trick):
        self.tricks.append(trick)

d = Dog('Fido')
e = Dog('Buddy')
print(d.kind) # shared by all dogs, 'canine'
print(e.kind) # shared by all dogs, 'canine'
print(d.name) # unique to d, 'Fido'
print(e.name) # unique to e, 'Buddy'
d.add_trick('roll over')
e.add_trick('play dead')
print(d.tricks) # unexpectedly shared by all dogs, ['roll over', 'play dead']

# Function defined outside the class
def f1(self, x, y):
    return min(x, x+y)

class C2:
    f = f1

    def g(self):
        return 'hello world'

    h = g
    
class Bag:
    def __init__(self):
        self.data = []

    def add(self, x):
        self.data.append(x)

    def addtwice(self, x):
        self.add(x)
        self.add(x)
        
class C3(C2): # derived class / inheritance
    def add(self, x):
        self.data.append(str(x)) # overrides method; now calling addtwice on a Bag will call this twice

class Mapping:
    def __init__(self, iterable):
        self.items_list = []
        self.__update(iterable)

    def update(self, iterable):
        for item in iterable:
            self.items_list.append(item)

    __update = update   # private copy of original update() method

class MappingSubclass(Mapping):

    def update(self, keys, values):
        # provides new signature for update()
        # but does not break __init__()
        for item in zip(keys, values):
            self.items_list.append(item)

class Employee:
    pass

john = Employee()  # Create an empty employee record

# Fill the fields of the record
john.name = 'John Doe'
john.dept = 'computer lab'
john.salary = 1000

for element in [1, 2, 3]:
    print(element)
for element in (1, 2, 3):
    print(element)
for key in {'one':1, 'two':2}:
    print(key)
for char in "123":
    print(char)
for line in open("myfile.txt"):
    print(line, end='')
print()

s = 'abc'
it = iter(s)
print(it) # <iterator object at 0x00A1DB50>
for i in range(len(s) + 1):
    try:
        print(next(it)) # 'a', 'b',  'c'
    except:
        print("Error:", sys.exc_info()[0])

class Reverse:
    """Iterator for looping over a sequence backwards."""
    def __init__(self, data):
        self.data = data
        self.index = len(data)

    def __iter__(self):
        return self

    def __next__(self):
        if self.index == 0:
            raise StopIteration
        self.index = self.index - 1
        return self.data[self.index]

rev = Reverse('spam')
print(iter(rev)) # <__main__.Reverse object at 0x00A1DB50>
for char in rev:
    print(char) # 'm', 'a', 'p', 's'

def reverse(data):
    for index in range(len(data)-1, -1, -1):
        yield data[index]

for char in reverse('golf'):
    print(char) # 'f', 'l', 'o', 'g'
    
print(sum(i*i for i in range(10))) # sum of squares, 285
xvec = [10, 20, 30]
yvec = [7, 5, 3]
print(sum(x*y for x,y in zip(xvec, yvec))) # dot product, 260

from math import pi, sin
sine_table = {x: sin(x*pi/180) for x in range(0, 91)}

data = 'golf'
print(list(data[i] for i in range(len(data)-1, -1, -1))) # ['f', 'l', 'o', 'g']

#----------------------------------------------------------------------------------------------------------------------------------------

# Section 10: StdLib 1


#----------------------------------------------------------------------------------------------------------------------------------------

# Section 11: StdLib 2


#----------------------------------------------------------------------------------------------------------------------------------------

# Section 12: Virtual Environments and Packages
