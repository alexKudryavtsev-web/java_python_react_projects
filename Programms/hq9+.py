path = input('Путь к файлу: ')
code = open(path, 'r').read()
count = 0


def poem(i):
    return str(i) + ' bottles of beer on the wall\n' + str(i) + \
           ' bottles of beer!\nTake one down,pass it around\n' + str(i - 1) + \
           ' bottles of beer on the wall!\n'


for expression in code:
    if expression == 'H':
        print('Hello, World')
    elif expression == 'Q':
        print(code)
    elif expression == '+':
        count += 1
    elif expression == '9':
        for i in list(range(1, 100)):
            print(poem(i))
