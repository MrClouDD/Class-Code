import BaseException

class ExitCode(Exception):
    pass
while True:
    try:
        possible_int = int(input("Enter an integer:"))
        if (possible_int == -1):
            raise exit_code
        print(f"\nYou entered {possible_int}\n")
    except ExitCode:
        break
    except ValueError:
        print("Could not turn input into an integer\n")
    