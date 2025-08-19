from typing import List

def count_even(ex_array: List[int]) -> int:
    count = 0
    for num in ex_array:
        if (num % 2 == 0):
            count += 1
    return count

if __name__ == "__main__":
    example = [1, 3, 5]
    print(f"There are {count_even(example)} even numbers in the list")
