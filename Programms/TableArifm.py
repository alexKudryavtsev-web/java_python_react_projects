
def make(file, start, end, *, column=4, onleft=True, operator=lambda x, y: x * y, sym='*'):
    # генерируем таблицу
    l = []
    for i in range(start, end):
        sub = []
        for j in range(start, end):
            sub.append("{0} {1} {2} = {3}".format(i, sym, j, operator(i, j)))
        l.append(sub)

    # выравниваем
    table = []
    for tr in l:
        m = len(max(tr, key=len))
        newtr = []
        for tb in tr:
            p = ' ' * (m - len(tb))
            s = tb + p if onleft else p + tb
            newtr.append(s)
        table.append(newtr)

    # записываем
    for el in zip(*table):
        for i in el:
            s = str(i) + '   '
            file.write(s)
        file.write('\n')
    file.close()


if __name__ == '__main__':
    make(open(r'C:\Users\Семья\PycharmProjects\Python Learn\progs\res.txt', 'w'),
     1, 15)
