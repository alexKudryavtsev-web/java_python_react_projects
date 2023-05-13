from calculator import states


class Context:
    def __init__(self):
        self._state = states.StateX()
        self._state.clear(self)
        self._x = 0
        self._y = 0
        self._oper = '+'

    def calc(self, expr):
        for i in list(expr):
            self.press(i)
        return self._x

    def press(self, key):
        if key == 'c':
            self._state.clear(self)
        elif key.isdigit():
            self._state.digit(self, key)
        elif key == '/' or key == '-' or key == '+' or key == '*' or key == '%':
            self._state.arifm(self, key)
        elif key == '=':
            self._state.equal(self)

    @property
    def state(self):
        return self._state

    @state.setter
    def state(self, state):
        self._state = state

    @property
    def x(self):
        return self._x

    @x.setter
    def x(self, x):
        self._x = x

    @property
    def y(self):
        return self._y

    @y.setter
    def y(self, y):
        self._y = y

    @property
    def operation(self):
        return self._oper

    @operation.setter
    def operation(self, operation):
        self._oper = operation

    def __str__(self):
        return 'x={0}, y={1}, op={2}, s={3}'. \
            format(self._x, self._y, self._oper, self._state)
