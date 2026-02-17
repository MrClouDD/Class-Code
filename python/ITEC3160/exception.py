while True:
    try:
        possible_int = int(input("Enter an integer:"))
        if (possible_int == -1):
            break
        print(f"\nYou entered {possible_int}\n")
    except ValueError:
        print("Could not turn input into an integer\n")
    