import math

def vigenere(message, key, mode=True):
    N = 65536
    normal_key = key_to_standart(key, len(message))
    code_message = split_string_to_codes(message)
    code_key = split_string_to_codes(normal_key)
    codes = zip(code_message, code_key)

    iter = None
    if(mode):
        iter = map(lambda c: plus_module(c[0], c[1], N), codes)
    else:
        iter = map(lambda c: diff_module(c[0], c[1], N), codes) 
    
    res = join_chars_code(iter)
    return res 

def split_string_to_codes(string):
    iter = map(lambda ch : ord(ch), string)
    return list(iter)    

def join_chars_code(chars):
    iter = map(lambda cd : chr(cd), chars)
    return "".join(iter)

def plus_module(a, b, n):
    res = a + b
    if(res > n):
        res = abs(n - res)
    return res

def diff_module(a, b, n):
    res = a - b
    if(res < 0):
        res = a + n - b
    return res      

def key_to_standart(key, n):
    length = len(key)
    count = math.trunc(n / length)
    remainder = n - length*count

    return (key * count) + key[:remainder]

if __name__ == "__main__":
    ms = vigenere("PROGRAMM", "DOG")
    print(vigenere(ms, "DOG", False))