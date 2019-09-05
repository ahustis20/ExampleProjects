# -------------------------------------------------
# CSCI 127, Joy and Beauty of Data
# Program 4: Advising System
# Abbey Hustis 
# Last Modified: November 7th, 2018
# -------------------------------------------------
# Students names are put in and whether or not they need help within their major or math and
# the resources that are available to them are given.
# -------------------------------------------------

class Generic_Major: #Generic major class
    def __init__(self, studentFirst, studentLast, majorTroubles = False, mathTroubles = False):
        self.firstName = studentFirst
        self.lastName = studentLast
        self.majTroub = majorTroubles
        self.mathTroub = mathTroubles
        
    def get_first_name(self):
        return self.firstName.capitalize()
    def get_last_name(self):
        return self.lastName.capitalize()
    def get_major(self):
        return "Generic"
    def get_major_troubles(self):
        return self.majTroub
    def get_math_troubles(self):
        return self.mathTroub
    def set_major_troubles(self, answer):
        self.majTroub = answer
        return self.majTroub
    def set_math_troubles(self, answer):
        self.mathTroub = answer
        return self.mathTroub
    def major_advising(self):
        if self.majTroub == True:
            print("because you are having troubles with your major:", "\n", 
               "--> visit your professor during office hours", "\n", 
               "--> visit your academic advisor")
        else:
            None

    def math_advising(self):
        if self.mathTroub == True:
            print("because you are having troubles with math:", "\n", 
               "--> visit the Math Learning Center in Wilson 1-112")
        else:
            None

class CLS_Major(Generic_Major): #CLS major class that inherits from generic
    def __init__(self, studentFirst, studentLast, majorTroubles = False, mathTroubles = False):
        super().__init__(studentFirst, studentLast, majorTroubles = False, mathTroubles = False)

    def get_major(self):
        return "College of Letters and Sciences"

class Physics_Major(CLS_Major): #Physics major class inherits from CLS and generic
    def __init__(self, studentFirst, studentLast, majorTroubles = False, mathTroubles = False):
        super().__init__(studentFirst, studentLast, majorTroubles = False, mathTroubles = False)

    def get_major(self):
        return "Physics"
    
    def major_advising(self): 
        if self.majTroub == True:
            Generic_Major.major_advising(self)
            print(" --> visit the Physics Learning Center in Barnard Hall 230")
        else:
            None
            
class COE_Major(Generic_Major): #COE major class that inherits from generic
    def __init__(self, studentFirst, studentLast, majorTroubles = False, mathTroubles = False):
        super().__init__(studentFirst, studentLast, majorTroubles = False, mathTroubles = False)

    def get_major(self):
        return "College of Engineering"

    def major_advising(self): 
        if self.majTroub == True:
            Generic_Major.major_advising(self)
            print(" --> visit the EMPower Student Center in Roberts 313")
        else:
            None

class Computer_Engineering_Major(COE_Major): #CE major class inherits from COE and generic
    def __init__(self, studentFirst, studentLast, majorTroubles = False, mathTroubles = False):
        super().__init__(studentFirst, studentLast, majorTroubles = False, mathTroubles = False)

    def get_major(self):
        return "Computer Engineering"

            
class Computer_Science_Major(COE_Major): #CS major class inherits from COE and generic
    def __init__(self, studentFirst, studentLast, majorTroubles = False, mathTroubles = False):
        super().__init__(studentFirst, studentLast, majorTroubles = False, mathTroubles = False)

    def get_major(self):
        return "Computer Science"
    
    def major_advising(self): 
        if self.majTroub == True:
            COE_Major.major_advising(self)
            print(" --> visit the CS Student Success Center in Barnard Hall 259", "\n",
                  "--> visit a CS professional advisor in Barnard Hall 357")
        else:
            None
     


# -------------------------------------------------
# Do not change anything below this line
# -------------------------------------------------

def advise(student):
    print("Student:", student.get_first_name(), student.get_last_name())
    print("Major: " + student.get_major() + ", Major Troubles: " +
          str(student.get_major_troubles()) + ", Math Troubles: " +
          str(student.get_math_troubles()))
    print()
    if not student.get_math_troubles() and not student.get_major_troubles():
        print("No advising is necessary.  Keep up the good work!")
    else:
        student.major_advising()
        student.math_advising()
    print("------------------------------")

# -------------------------------------------------

def process(student):
    advise(student)
    student.set_major_troubles(True)
    student.set_math_troubles(True)
    advise(student)

# -------------------------------------------------

def main():

    # Every student has a major, even if it is "undeclared"
    msu_student = Generic_Major("jalen", "nelson")
    process(msu_student)

    msu_student.set_math_troubles(False)
    advise(msu_student)

    msu_student.set_math_troubles(True)
    msu_student.set_major_troubles(False)
    advise(msu_student)

    # A CLS (College of Letters and Science) major is a subclass of a Generic major
    msu_student = CLS_Major("emma", "phillips")
    process(msu_student)

    # A COE (College of Engineering) major is a subclass of a Generic major
    msu_student = COE_Major("camden", "miller")
    process(msu_student)

    # A Computer Engineering major is a subclass of a COE major
    msu_student = Computer_Engineering_Major("gabriel", "smith")
    process(msu_student)

    # A Physics major is a subclass of a CLS major
    msu_student = Physics_Major("lena", "hamilton")
    process(msu_student)

    # A Computer Science major is a subclass of a COE major
    msu_student = Computer_Science_Major("halley", "jones")
    process(msu_student)

    msu_student.set_math_troubles(False)
    advise(msu_student)

    msu_student.set_math_troubles(True)
    msu_student.set_major_troubles(False)
    advise(msu_student)

# -------------------------------------------------

main()

