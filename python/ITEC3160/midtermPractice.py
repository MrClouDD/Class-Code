from abc import ABC, abstractmethod

class Animal(ABC):
    kingdom = "Animalia"
    
    def __init__(self, name, sound):
        self.name = name
        self.sound = sound
    
    @abstractmethod
    def speak(self):
        pass
    
    @abstractmethod
    def __str__(self):
        pass
    
class Dog(Animal):
    def __init__(self, name):
        super().__init__(name, "woof")
        
    def speak(self):
        print(self)
        
    def __str__(self):
        return f"The dog named {self.name} goes {self.sound}!"
    
class Cat(Animal):
    def __init__(self, name):
        super().__init__(name, "meow")
        
    def speak(self):
        print(self)
        
    def __str__(self):
        return f"The cat named {self.name} goes {self.sound}!"

animal_list = [Dog("Maxie"), Dog("Johny"), Cat("Missy"), Cat("George")]
for obj in animal_list:
    obj.speak()
