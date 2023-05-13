from string import ascii_uppercase


def encrypt(value, key):
    ret = ''
    vs = to_list_int(value)
    ks = to_list_int(make_key(key, len(value)))
    for index, value in enumerate(vs):
        d = plus_on_module(value, ks[index], 26)
        ret += ascii_uppercase[d]
    return ret


def decrypt(value, key):
    ret = ''
    vs = to_list_int(value)
    ks = to_list_int(make_key(key, len(value)))
    for index, value in enumerate(vs):
        d = value - ks[index]
        if d > 26:
            d = value + 26 - ks[index]
            print(d)
        ret += ascii_uppercase[d]
    return ret


def make_key(key, length):
    count = length // len(key)
    rem = length % len(key)
    ret = []
    for _ in range(count):
        ret.extend(key)
    ret.extend(key[:rem])
    return ret


def to_list_int(string):
    ret = []
    for char in list(string):
        ret.append(ascii_uppercase.index(char.upper()))
    return ret


def plus_on_module(f1, f2, limit):
    res = f1 + f2
    return res if res < limit else res - limit


if __name__ == '__main__':
    print(encrypt('PROGRAM', 'DOG'))
    print(decrypt('SFUJFGP', 'DOG'))
