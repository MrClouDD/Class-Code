"""Dictionaries:

    Take input from a user in a loop
    Add to a dictionary items and their associated values
    Then check if items exist in the dictionary
    Delete items from dictionary based on conditions
    Print out the dictionary

Also include the following elements:

    A function that returns multiple values using a tuple
    Use list unpacking to assign variables from tuple
    Access a list’s elements by using slicing
"""

example_dict = {"Erik": 24, "Xavier": 25, "Tim": 23, "Jake":45}

def main():
    #User input
    user_defined_dict = add_to_dict()
    
    #Check for existence
    key_exists("Erik",user_defined_dict,True)

    # Deletion Behavior
    print("\nBefore deletion:\n")
    print_dict(user_defined_dict)
    returned = delete_item(lambda x: "Q" in x, user_defined_dict, True, "keys containing 'Q'")
    if returned:
        print_dict(user_defined_dict)

    #Convert to list and print forewards and backwards
    print()
    print_dict_list(splice_list(dict_to_list(user_defined_dict)))
    print_dict_list(splice_list(dict_to_list(user_defined_dict), None, None, -1))



def add_to_dict():
    dict = {}
    while True:
        key = input("Type in your key (Enter w/o anything to exit)\t")
        if(key.strip() == ""):
            break
        value = input(f"Type in the value for {key}\t")
        dict[key] = value
    return dict

def key_exists(key, dict=example_dict, print_out=False):
    """ Check if key exists """
    if key in dict:
        if print_out:
            print(f"{key} found")
        return True
    else:
        if print_out:
            print(f"{key} does not exist")
        return False
        
def delete_item(condition, dict=example_dict, print_out=False, description="condition"):
    found_any = False
    for key in list(dict.keys()):
        if condition(key):
            if print_out:
                print(f"Deleting {key} in dictionary")
            del dict[key]
            found_any = True
    if not found_any:
        if print_out:
            print(f"No key found that matches {description}")
        return False
    return True

def print_dict(dict=example_dict):
    for key in dict:
        print(f"The {key} has a {dict[key]}")
    print()

def return_tuple(key, dict=example_dict):
    if key_exists(key, dict):
        return key, dict[key]
    
def dict_to_list(dict=example_dict):
    keys = []
    values = []
    dict_list = [keys,values]

    for key in dict:
        key_name, value = return_tuple(key, dict)
        keys.append(key_name)
        values.append(value)
    return dict_list

def print_dict_list(dict_list):
    i = 0
    for key in dict_list[0]:
        print(f"The list has a {key} key with a value of {dict_list[1][i]}")
        i += 1
    print()

def splice_list(list, first=0, last=None, step=1):
    return [list[0][first:last:step], list[1][first:last:step]]

if __name__ == "__main__":
    help(key_exists)