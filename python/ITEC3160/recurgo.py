reverse_dict = {1: "right", 2: "back", 3: "left"}
reversed_directions = []

def go():
    try:
        direction = int(input("Enter direction (1 for left, 2 for straight, 3 for right, and done to stop): "))
        reversed_directions.append(direction)
        
        if direction == 1:

            print("Went left!")
            go()
            print("Went right!")
        elif direction == 2:
            print("Went straight!")
            go()
            print("Went back!")
        elif direction == 3:
            print("Went right!")
            go()
            print("Went left!")

    except ValueError:
        reversed_directions.reverse()
        print("Arrived, returning!")
        
        

