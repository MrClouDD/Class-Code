class PythonClass:
  school = "GGC"
  degree = "IT"
  classname = "ITEC 3160"
  members = []

  def __init__(self, fname, lname):
    self.fname = fname
    self.lname = lname
    self.member = "student"
    PythonClass.members.append(self)

  def __str__(self):
    return self.fname + " " + self.lname + " (" + self.member + ")"

class ThisClass(PythonClass):
    def __init__(self, first_name, last_name, grade):
        super().__init__(first_name, last_name)
        self.grade = grade
        
    def __str__(self):
        return(f"{self.fname} {self.fname}'s grade in {self.classname} will, hopefully, be a/an {self.grade}")
        
gradeA = ThisClass("Ajitesh", "Sandhu", "A")

print(gradeA)