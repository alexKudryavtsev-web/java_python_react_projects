NOT_PALINDROME_NUMBER = frozenset([
        196, 295, 394, 493, 592, 
        689, 691, 788, 790, 879,
        887, 978, 986, 1495, 1497, 
        1585, 1587, 1675, 1677, 
        1765, 1767, 1855, 1857, 
        1945, 1947, 1997
])

def palindrome(num):
    if num in NOT_PALINDROME_NUMBER:
        return None
    
    if is_palindrome(num):
        return num
    
    while not is_palindrome(num):
        rnum = int(str(num)[::-1])
        num = num + rnum
    
    return num

def is_palindrome(num):
    str_num = str(num)
    rnum = str_num[::-1]
    return str_num == rnum
