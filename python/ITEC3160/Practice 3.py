"""
    - A set: add elements and check for membership
    - An if-elif-else statement
    - A loop with use of break and continue
    - Use truthiness of data structures in a conditional statement (other than simple binary operators); could be for loop logic
    - sort a data structure and print them out
    - create a list using the list comprehension syntax; you can use the data from the set
    - use zip somewhere
    - specify type annotations in at least one place
    - make sure it's sufficiently distinct from others' submissions and that your program has a theme        
"""

game_library = {"Destiny 2", "The Division", "Factorio", "Hytale"}

session_list = [("Destiny 2", 10), ("The Division", 6), ("Factorio", 30), ("Hytale", 2), ("Helldivers", 1)]

total_hours = {}

def add_session() -> None:
    input_name = input("Enter the game you played:\t")
    input_hours = int(input("Enter # of hours this session:\t"))
    
    if input_name not in game_library:
        game_library.add(input_name)
    session_list.append((input_name, input_hours))
    
def list_games():
    sorted_games = sorted(game_library)
    count = [x + 1 for x in range(len(sorted_games))]
    
    full_list = list(zip(count, sorted_games))
    
    for num, game in full_list:
        print(f"{num}) {game}")

def total_by_hours():
    total_hours.clear()
    
    for session in session_list:
        name = session[0]
        hours = session[1]
        
        if name in total_hours:
            total_hours[name] = total_hours[name].value + hours
        else:
            total_hours[name] = hours
            
    sorted_total_hours = sorted(total_hours.items(), key=lambda x: x[1], reverse=True)

    for total in sorted_total_hours:
        print(f"Name: {total[0]:<20} | Total hours: {total[1]:>2}")

while True:
    menu_command = input("Enter a command. Type 'help' to get a list of commands\n")    
    if (menu_command.lower() == "add"):
        add_session()
    elif (menu_command.lower() == "list"):
        list_games()
    elif (menu_command.lower() == "total"):
        total_by_hours()
    elif (menu_command.lower() == "quit"):
        break
    elif (menu_command.lower() == "help"):
        print("Commands:")
        print("\t'add' to add a game session")
        print("\t'list' to list all sessions")
        print("\t'total' to show total hours in all games")
        print("\t'quit' to quit the program")
    else: 
        print("Command not recognized")
        continue
