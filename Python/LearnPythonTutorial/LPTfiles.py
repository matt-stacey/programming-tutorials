# names and positions from The Office

employee_file = open("employees.txt", "r")
employee_file.close()

with open("employees.txt", "r") as employee_file:
    print(employee_file.readable())
    for employee in employee_file.readlines():
        print(employee)

employee_name = "Toby"
employee_job = "Human Resources"
with open("employees.txt", "a") as employee_file:
    employee_file.write(f"{employee_name} - {employee_job}\n")

employee_name = "Kelly"
employee_job = "Customer Service"
with open("employees.txt", "w") as employee_file:
    employee_file.write(f"{employee_name} - {employee_job}\n")
    
employees = ["Jim", "Dwight", "Pam", "Michael", "Oscar", "Toby", "Kelly"]
jobs = ["Salesman", "Salesman", "Receptionist", "Manager", "Accountant", "Human Resources", "Customer Service"]

with open("employees.txt", "w") as employee_file:
    for employee, job in zip(employees, jobs):
        employee_file.write(f"{employee} - {job}\n")