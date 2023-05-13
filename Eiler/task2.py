def t1(limit=4000000, initial=0):
    f1, f2 = 1, 1
    while f1 < limit:
        f1, f2 = f2, f1 + f2
        if f2 % 2 == 0:
            initial += f2
    return initial


print(t1())
