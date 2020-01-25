class Student:
    
    def __init__(self, name, major, gpa, is_on_probation):
        # initialization function
        self.name = name
        self.major = major
        self.gpa = gpa
        self.is_on_probation = is_on_probation
    
    def on_honor_roll(self):
        if self.gpa >= 3.5:
            return True
        else:
            return False

class Question:
    def __init__(self, prompt, answer):
        self.prompt = prompt
        self.answer = answer
        
class Chef:
    def __init__(self):
        pass
    
    def make_chicken(self):
        print("The Chef makes a chicken")
        
    def make_salad(self):
        print("The Chef makes a salad")
        
    def make_special_dish(self):
        print("The Chef makes bbq ribs")
        
class ChineseChef(Chef):
    def __init__(self):
        pass
    
    def make_fried_rice(self):
        print("The Chef makes fried rice")
    
    def make_special_dish(self):
        print("The Chef makes orange chicken")