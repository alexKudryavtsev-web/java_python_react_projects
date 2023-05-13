def t1():
    f1, f2 = 0, 0
    for i in range(1, 101):
        f1 += (i * i)
        f2 += i
    return f2 * f2 - f1


print(t1())
