def check(i):
    return (i % 2 == 0) and \
           (i % 3 == 0) and \
           (i % 5 == 0) and \
           (i % 7 == 0) and \
           (i % 11 == 0) and \
           (i % 13 == 0) and \
           (i % 17 == 0) and \
           (i % 19 == 0)


def t1():
    i = 2520
    while not check(i):
        i += 1
    return i


print(t1())
