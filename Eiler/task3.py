import math


def issimple(a):
    r = math.ceil(math.sqrt(a))
    lst = []
    for i in range(3, r):
        if a % i == 0:
            if issimple(i) == []:
                lst.append(i)
    return lst


r = issimple(1319224425)
print(max(r))