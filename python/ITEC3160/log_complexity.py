# O(log n)
# Calculate power using recursion

def power(base, expnt):
    if expnt == 0:
        return 1
        
    half = power(base, expnt // 2)
    
    if expnt % 2 == 0:
        return half * half
    else:
        return half * half * base

print(power(2, 3)) #Prints 8