from collections import *

Analysis = namedtuple('Analysis', ['alphabet', 'digits', 'case'])


def analysis(path_to_file, map_function=lambda dictionary: dictionary):
    res = dict()
    for line in open(path_to_file, 'r'):
        res[line] = Analysis(count_alphabet(line), count_digits(line), case(line))
    return map_function(res)


def case(string):
    if string.islower():
        return 'lower'
    elif string.isupper():
        return 'upper'
    return 'mixed'


def count_alphabet(string):
    count = 0
    for c in string:
        if c.isalpha():
            count += 1
    return count


def count_digits(string):
    count = 0
    for c in string:
        if c.isdigit():
            count += 1
    return count


def count_alphabet(string):
    count = 0
    for c in string:
        if c.isalpha():
            count += 1
    return count
