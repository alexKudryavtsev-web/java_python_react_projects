def calc(day, percent):
    if percent == 100:
        return day
    total = 1
    work = []
    for i in range(1, day + 1):
        total, sub = total * 2, total * 2 - total
        work.append(sub)

    total *= percent / 100
    s = 0
    for i, v in enumerate(work):
        s += v
        if s >= total:
            return i


print(calc(18, 90))