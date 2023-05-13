from collections import namedtuple

# func_to переводит name в милиграммы, а func_from обратное
Unit = namedtuple('Unit', ['name', 'func_to', 'func_from'])


def get(name, value, more):
    if more:
        return Unit(name, lambda i: i * value, lambda i: i / value)
    else:
        return Unit(name, lambda i: i / value, lambda i: i * value)


converters = [
    Unit('Mg', lambda mg: mg, lambda mg: mg),
    get('G', 1000, True),
    get('Kg', 10 ** 6, True),
    get('t', 1e9, True),
    get('μg', 1000, False)
]


def convert(type_value, num, need_type):
    if type_value == need_type:
        return num
    handler1 = None
    handler2 = None
    for i in converters:
        if i.name == type_value:
            handler1 = i
        if i.name == need_type:
            handler2 = i

    return handler2.func_from(handler1.func_to(num))


if __name__ == '__main__':
    print(convert('Mg', 12000000000, 't'))
