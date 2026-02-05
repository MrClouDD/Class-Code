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

    Game Ledger:
        zip for menu
        Data:
            Unique set of games.
            dict of total hours.
            tuple list of sessions.
        Add:
            Name, Hours
            Check name in set, if not add to set.
        leadboards
        quit
        
"""
menu_command = ""
while True:
    if (menu_command == "add".lower):
        # Adding session
        print("TODO")
    elif (menu_command.lower == "list"):
        # show list of games
        print("TODO")
    elif (menu_command.lower == "leaderboard"):
        # show leadboard (list sorted my descending total hours)
        print("TODO")
    elif (menu_command.lower == "quit"):
        break
    elif (menu_command.lower == "help"):
        print("Commands")
        print("'add' to add a game session")
        print("'list' to list all sessions")
        print("'leaderboard' to list games in order of most hours played")
    else: 
        print("Command not recognized")
        continue
