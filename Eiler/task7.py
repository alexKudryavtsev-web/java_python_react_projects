import math


def t1(initial=0):
    v, count = 2, 1
    while count != 10001:
        if check(v):
            count += 1
            initial = v
            v += 1
    return initial


def check(a):
    i = 2
    limit = int(math.sqrt(a))

    while i <= limit:
        if a % i != 0:
            i += 1
        else:
            return False
    return True


print(t1())
