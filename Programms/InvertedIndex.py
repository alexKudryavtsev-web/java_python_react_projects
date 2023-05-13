from collections import namedtuple

Res = namedtuple('Res', ['key', 'file', 'index', 'count'])


def calc(request, *files):
    s = ''
    for i in request:
        if i.isalpha() or i.isspace():
            s += i

    keys = s.split(' ')
    ret = {}
    texts = map(read, files)
    print(set(texts))


def read(f):
    with open(f, 'r') as file:
        s = f, file.read()
    return s


# calc('ff f bfd', 'ideas.txt', 'res.txt')

print(open(r'C:\Users\Семья\PycharmProjects\Python Learn\progs\ideas.txt', 'r').read())