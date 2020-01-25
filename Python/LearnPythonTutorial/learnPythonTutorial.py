# Learn Python - Full Course for Beginners
# freeCodeCamp.org
# www.youtube.com/watch?v=rfscVS0vtbw

import LPTapp
import LPTstrings
import LPTnumbers
import LPTinput

import LPTlists
import LPTfunctions
import LPTflowcontrol

import LPTerrors
import LPTfiles

from LPTclasses import Student
students = [
    Student("Jim", "Business", 3.1, False),
    Student("Pam", "Art", 2.5, True),
    Student("Oscar", "Accounting", 3.1, False),
    Student("Phyllis", "Business", 3.8, False)
    ]
for stud in students:
    print(f"{stud.name} honor roll status: {stud.on_honor_roll()}")

from LPTclasses import Chef, ChineseChef
myChef = Chef
myChef.make_chicken()

myChineseChef = ChineseChef
myChineseChef.make_chicken()
myChineseChef.make_fried_rice()
myChineseChef.make_special_dish()

# basic/improved calculator
import calculator

# mad libs
import madlibs

# guessing game
import guessinggame

# translator
import translator

# multiple choice quiz
import mcquiz