import random


def generate():
    suits = ['♠', '♣', '♥', '♦']
    types = ['ace', '2', '3', '4', '5', '6', '7', '8', '9', '10', 'King', 'Queen', 'Jack']

    cards = []
    for suit in suits:
        for t in types:
            cards.append(suit + ' ' + t)
    return cards


def make_cards(users, quantity, cards=generate()):
    need_cars = users * quantity
    if need_cars > len(cards):
        raise ValueError('users * quantity > len(cards)')
    random.shuffle(cards)
    sel_cards = cards[:users * quantity]
    ret = []
    index = 0
    for _ in range(users):
        fin = index + quantity
        ret.append(
            CardIter(sel_cards[index:index + quantity])
        )
        index = fin
    ret.append(
        CardIter(cards[index:])
    )
    return ret


class CardIter:
    def __init__(self, data):
        self._data = data
        self._index = 0

    def __iter__(self):
        return self

    def __next__(self):
        if self._index < len(self._data):
            value = self._data[self._index]
            self._index += 1
            return value
        raise StopIteration

    def show(self):
        print(self._data)


if __name__ == '__main__':
    l = make_cards(2, 5)
    for iterable in l:
        iterable.show()
