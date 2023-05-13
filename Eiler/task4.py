def palindrome(a):
    s = str(a)
    return s == s[::-1]


def t1(initial=float('-inf')):
    for i in range(100, 1000):
        for j in range(100, 1000):
            v = i * j
            if v > initial and palindrome(v):
                initial = v
    return initial


print(t1())
