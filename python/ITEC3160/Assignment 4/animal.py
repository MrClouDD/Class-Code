from abc import ABC, abstractmethod

class Animal(ABC):
    all_animals = {}
    
    def __init__(self, name):
        self.name = name

        repr_str = self.__repr__()
        
        if repr_str in Animal.all_animals:
            counter = 1
            original_name = self.name
            while repr_str in Animal.all_animals:
                self.name = f"{original_name}_{counter}"
                repr_str = self.__repr__()
                counter += 1
        Animal.all_animals[repr_str] = self
    
    @abstractmethod
    def __repr__(self):
        pass
        
    @staticmethod
    def listAnimals():
        result = "All animals: "
        for key in Animal.all_animals.keys():
            result += f"{key}, "
        return result.rstrip(", ")
            
class Kennel():
    cat_num = 0
    dog_num = 0
    
    def add(self, anim):
        if isinstance(anim, Dog): 
            if Kennel.dog_num < 10:
                Kennel.dog_num += 1
            else:
                print(f"Error: Kennel has {Kennel.dog_num} dogs already!")
        elif isinstance(anim, Cat):
            if Kennel.cat_num < 5:
                Kennel.cat_num += 1
            else:
                print(f"Error: Kennel has {Kennel.cat_num} cats already!")
            
class Dog(Animal):
    def __init__(self, name, breed):
        self.breed = breed
        super().__init__(name)
    
    def __repr__(self):
        return f"Dog({self.name}, {self.breed})"
        
class Cat(Animal):
    def __init__(self, name, breed):
        self.breed = breed
        super().__init__(name)
    
    def __repr__(self):
        return f"Cat({self.name}, {self.breed})"