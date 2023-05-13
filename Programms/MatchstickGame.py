from random import randint


def generate(count):
    return [randint(1, 100) for _ in range(count)]


def calc(count, *gamers):
    r = {key: 0 for key in gamers}
    p = 0
    for item in generate(count):
        key = gamers[p]
        r[key] = r[key] + item
        if p < len(gamers) - 1:
            p += 1
        else:
            p = 0
    return r


if __name__ == '__main__':
    print(calc(15, 'Python', 'Artem', 'Alex', 'Rustam'))