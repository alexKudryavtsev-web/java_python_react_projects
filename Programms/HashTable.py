import abc


class Table:

    def __init__(self, max_size, iterable):
        self.arr = [0 for _ in range(max_size)]
        for item in iterable:
            self.arr[item.__hash__()] = item

    def __add__(self, other):
        pass

    def remove(self, key):
        pass

    def get(self, key):
        return self.arr[key.__hash__()]

    def values(self):
        return [item for item in self.arr]

    def keys(self):
        pass
