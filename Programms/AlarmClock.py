import time
import threading
from datetime import datetime
from datetime import timedelta


class AlarmClock(threading.Thread):
    def __init__(self, end_time, *args, **kwargs):
        super().__init__(*args, **kwargs)
        if datetime.now() > end_time:
            raise ValueError('end should > now')
        if datetime.now() == end_time:
            raise ValueError('end_time == now')
        self._delta = (end_time - datetime.now()).total_seconds()

    def run(self):
        point = 0
        while point <= self._delta:
            time.sleep(1)
            point += 1

        print('Встаем')


if __name__ == '__main__':
    d1 = datetime(2019, 7, 13, 18, 20)
    AlarmClock(d1).start()
