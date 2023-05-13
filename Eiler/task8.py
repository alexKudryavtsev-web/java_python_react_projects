from timeit import timeit
from itertools import combinations


# вариант для больших цифр
def t1(number, count):
    ret = float('-inf')
    for i in combinations(number, count):
        s, v = '', 0
        for j in i:
            s += j
            v += int(j)
        if s in number:
            if v > ret:
                ret = v
    return ret


# вариант для маленьких цифр
def t2(number, count):
    index = 0
    ret = float('-inf')
    while index < len(number) - count:
        v = number[index: index + count]
        r = 0
        for i in v:
            r += int(i)
        if r > ret:
            ret = r
        index += 1
    return ret


print(timeit('t1(\'4424254524524524\', 10)', number=1, globals=globals()))
print(timeit('t2(\'4424254524524524\', 10)', number=1, globals=globals()))

print(timeit('t1(\'1111111\', 2)', number=1, globals=globals()))
print(timeit('t2(\'1111111\', 2)', number=1, globals=globals()))