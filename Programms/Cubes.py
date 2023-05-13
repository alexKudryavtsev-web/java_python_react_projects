import timeit


# линейные вариант
def calc_line(r):
    s = 0
    for i in range(1, r + 1):
        s += i * i
    return s


# Более быстрый вариант
def calc_iter(r):
    return sum([i ** 2 for i in range(1, r + 1)])


r = timeit.timeit('calc_iter(16)', number=1, globals=globals())
print(r)

r1 = timeit.timeit('calc_line(16)', number=1, globals=globals())
print(r1)

