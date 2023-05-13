import re
import decimal
from decimal import Decimal
from collections import OrderedDict


def make_table(text):
    clear_string = ''
    unique_sym = set()
    ret = {}

    for i in text.lower():
        if i.isalpha():
            clear_string += i
            unique_sym.add(i)

    for i in unique_sym:
        substring_re = '(?=(%s))' % re.escape(i)
        num = len(re.findall(substring_re, clear_string))

        r = Decimal(num / len(clear_string) * 100). \
            quantize(Decimal('1.00000'), decimal.ROUND_HALF_EVEN)
        ret[i] = float(r)

    sort = sorted(ret.items(), key=lambda x: x[1])

    return OrderedDict(sort)


s1 = open(input('Зашифрованный файл:'), 'r').read()
s2 = open(input('Аналоговый файл: '), 'r').read()
d = make_table(s1)
d1 = make_table(s2)

r = {}

for i in zip(d, d1):
    key, value = i
    r[ord(key)] = value
    r[ord(key.upper())] = value.upper()

print(s2.translate(r))
