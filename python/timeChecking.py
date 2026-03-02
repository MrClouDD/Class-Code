import timeit

def power(base, expnt):
    if expnt == 0:
        return 1
        
    half = power(base, expnt // 2)
    
    if expnt % 2 == 0:
        return half * half
    else:
        return half * half * base

time100 = timeit.timeit('power(2, 100)', setup='from __main__ import power', number=100)
print(f"Time for power(2, 100) repeated 100 times: {time100} seconds")

time1000 = timeit.timeit('power(2, 1000)', setup='from __main__ import power', number=1000)
print(f"Time for power(2, 1000) repeated 1000 times: {time1000} seconds")

time10000 = timeit.timeit('power(2, 10000)', setup='from __main__ import power', number=10000)
print(f"Time for power(2, 10000) repeated 10000 times: {time10000} seconds")

time100000 = timeit.timeit('power(2, 100000)', setup='from __main__ import power', number=100000)
print(f"Time for power(2, 100000) repeated 100000 times: {time100000} seconds")

""" Time for my PC:
Time for power(2, 100) repeated 100 times: 4.78999936603941e-05 seconds
Time for power(2, 1000) repeated 1000 times: 0.0010735999967437238 seconds
Time for power(2, 10000) repeated 10000 times: 0.09206970000377623 seconds
Time for power(2, 100000) repeated 100000 times: 13.747522199999366 seconds
"""