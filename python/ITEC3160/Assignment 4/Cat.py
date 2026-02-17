import Animal

class Dog(Animal):
    def __init__(self, breed):
        super.__init__(name)
        self.breed = breed
    
    
    def __repr__(self):
        print(f"Cat({self.name}, {self.breed})")