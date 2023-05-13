def kollatc(n):
    if n == 0:
        return None

    r = n
    history = [r]

    while r != 1:
        if r % 2 == 0:
            r = r / 2
        else:
            r = 3*r + 1
        history.append(r)
    return history
