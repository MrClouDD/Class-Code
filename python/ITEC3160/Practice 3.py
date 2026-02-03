"""
    A set: add elements and check for membership
    An if-elif-else statement
    A loop with use of break and continue
    Use truthiness of data structures in a conditional statement (other than simple binary operators); could be for loop logic
    sort a data structure and print them out
    create a list using the list comprehension syntax; you can use the data from the set
    use zip somewhere
    specify type annotations in at least one place
    make sure it's sufficiently distinct from others' submissions and that your program has a theme
"""



def main():
    print("main")

def add_to_set():
    """ Add to classes set. Check for membership before adding. Implements a stop by comparing with the 'falsy' of the string input
    """
    classes = {"ECON2100", "ITEC3160", "MATH2600", "MATH2500"}

    hours_per_week = {5, 8, 10, 12}

    cont = True

    while cont:
        class_to_add = input("Enter the class code to add (press enter without anything to exit):")

        if (class_to_add is None):
            cont = False

        while class_to_add in classes:
            print(f"{class_to_add} already exists. Enter a new class code:")
        
        hours_to_add = input(f"Enter the # of hours spent per week for {class_to_add}")
        hours_per_week.add(hours_to_add)

    


        
