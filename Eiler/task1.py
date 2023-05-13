def t1(limit=1000):
    res = 0
    for i in range(1, limit):
        if (i % 3 == 0) or (i % 5 == 0):
            res += i
    return res


def t2(limit=1000):
    return sum([i for i in range(1, limit)
                if (i % 3 == 0) or (i % 5 == 0)])


if __name__ == '__main__':
    print(t1())
    print(t2())