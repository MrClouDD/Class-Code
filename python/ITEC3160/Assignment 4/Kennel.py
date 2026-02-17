import Animal, Dog, Cat

class Kennel():
    cat_num = 0
    dog_num = 0
    
    def add(anim):
        if anim is Dog: 
            if dog_num < 10:
                dog_num += 1
            else:
                print(f"Error: Kennel already has {dog_num} dogs")
        elif anim is Cat:
            if cat_num < 5:
                cat_num +=1
            else:
                print(f"Error: Kennel already has {cat_num} cats")
            