def calc(value, data, predicate=lambda x: True):
    s = 1
    while value != 1:
        value -= 1
        print(value)
        s *= value
        if predicate(s):
            data.append(s)
    return s


d = []
print(13, calc(5, d))
print(d)
