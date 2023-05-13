from collections import namedtuple
from decimal import Decimal
from decimal import ROUND_HALF_UP

BMI = namedtuple('BMI', ['bmi', 'type'])


def calc(weight, growth):
    d = Decimal(weight / ((growth / 100) ** 2))
    d = float(d.quantize(Decimal('1.0'), ROUND_HALF_UP))

    if 16.0 < d < 18.5:
        type_bmi = 'Under weight'
    elif 18.5 < d < 25:
        type_bmi = 'Normal'
    elif 25 < d < 40:
        type_bmi = 'Over weight'
    else:
        type_bmi = 'Non correlated data'
    return BMI(d, type_bmi)


if __name__ == '__main__':
    weight = int(input('Вес(кг):'))
    height = int(input('Рост(см):'))
    res = calc(weight, height)
    print(res)
