import random

COUNT_PRIMARY = 300
COUNT_E = 200

def is_primary(num):
    if num < 2:
        return False
    for x in range(2, int(num**0.5)+1):
        if num % x == 0:
            return False
    return True

def random_primary():
    i = 0
    sequence = []
    while len(sequence) <= COUNT_PRIMARY:
        i += 1
        if is_primary(i):
            sequence.append(i)
    
    return random.choice(sequence)

def public_key():
    p = random_primary()
    q = random_primary()
    mod = p * q
    f = (p - 1) * (q - 1)

    e = None
    i = 0
    e_list = []
    while len(e_list) < COUNT_E:
        i += 1
        if is_primary(i) and f % i != 0:
            e_list.append(i)
    
    e = random.choice(e_list)

    return mod, e, f

def private_key(mod, f, e):
    d = 1
    while (d * e) % f != 1:
        d += 1
    return d

def generate_key():
    mod, e, f = public_key()
    d = private_key(mod, f, e)
    return mod, e, d

def encrypt(ms, mod, e):
    res = []
    for i in ms:
        code = ord(i)
        if code > mod:
            raise Exception()
        res.append(code ** e % mod)

    return res

def decipher(ms, mod, d):
    res = ''
    for i in ms:
        res = res + chr(i ** d % mod)
    return res

# for example
if __name__ == '__main__': 
    ms = input('Input text: ')
    mod, e, d = generate_key()

    x = encrypt(ms, mod, e)
    answ = decipher(x, mod, d)
    print(x)
    print(f"mod: {mod}, d: {d}")
