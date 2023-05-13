import html
import json
import time
import threading


class Timer(threading.Thread):

    def __init__(self, seconds, **other):
        super().__init__(**other)
        self._seconds = seconds
        self._flag = False
        self._pointer = 0

    def run(self):
        while self._pointer < self._seconds:
            if not self._flag:
                self._pointer += 1
                time.sleep(1)
                print('{0}: {1}'.format(self.name, self._pointer))

    def stop(self):
        self._flag = True

    def resume(self):
        self._flag = False

    def reset(self, second):
        self._seconds = second


if __name__ == '__main__':
    th = Timer(4, name='Лох')
    th.start()
