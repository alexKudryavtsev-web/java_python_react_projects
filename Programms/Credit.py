import math


def diff_payment(money, month, percent):
    ret = {}
    diff_part = money / month
    pay_bal = money

    i = 1
    s = 0
    while i <= month:
        var_part = pay_bal * percent / (100 * 12)
        value = diff_part + var_part
        ret[i] = value
        pay_bal -= diff_part
        i += 1
        s += value
    return s / len(ret), ret


def annuity_payment(money, month, percent):
    i = percent / 12 / 100
    print(i)
    k = (
                i * math.pow(1 + i, month)
        ) / (
                math.pow(1 + i, month) - 1
        )
    return math.floor(k * money)
