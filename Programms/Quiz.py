import re
import random


class Quiz:
    def __init__(self, file, format_file=r'(.+): (\w+)'):
        self._data = {}
        for line in file.read().splitlines():
            quest, answ = re.findall(format_file, line).pop(0)
            self._data[quest] = answ

    @property
    def storage(self):
        return self._data

    def choice(self, n):
        res = set()
        keys = list(self._data.keys())
        while len(res) <= n:
            res.add(random.choice(keys))
        return res

    def check(self, **kwargs):
        res = []
        for key in kwargs:
            res.append(kwargs[key].strip(' \t\n\r') == self._data[key])
        return res


if __name__ == '__main__':
    file = open(input('Файл с данными:'), 'r')
    count = int(input('Количество вопросов:'))

    q = Quiz(file)
    s = q.choice(count)
    d = {}

    for i in s:
        print('Вопрос:', i)
        d[i] = input('Ответ:')

    print(q.check(**d))
