from time import sleep
from itertools import count
from threading import Thread


class Stopwatch(Thread):
    def __init__(self, initial=0, *args, **kwargs):
        super().__init__(*args, **kwargs)
        self._counter = count(initial)
        self._running = True
        self._result = []

    def run(self):
        while self._running:
            v = next(self._counter)
            print(v)
            self._result.append(v)
            sleep(1)

    def stop(self):
        self._running = False
        return self._result


if __name__ == '__main__':
    s = Stopwatch(10)
    s.start()

    sleep(3)
    print(list(s.stop()))
