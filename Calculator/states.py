import abc
from math import floor


class State(abc.ABC):
    @abc.abstractmethod
    def clear(self, context):
        pass

    @abc.abstractmethod
    def digit(self, context, key):
        pass

    @abc.abstractmethod
    def arifm(self, context, key):
        pass

    @abc.abstractmethod
    def equal(self, context):
        pass


class StateAnswer(State):
    def arifm(self, context, key):
        context.state = StateAction()
        context.state.arifm(context, key)

    def equal(self, context):
        op = context.operation
        if op == '-':
            context.x = context.x - context.y
        elif op == '+':
            context.x = context.x + context.y
        elif op == '/':
            context.x = floor(context.x / context.y)
        elif op == '*':
            context.x = context.x * context.y
        elif op == '%':
            context.x = context.x % context.y

    def digit(self, context, key):
        context.state = StateX()
        context.state.digit(context, key)

    def clear(self, context):
        context.state = StateX()
        context.state.clear(context)


class StateAction(State):
    def arifm(self, context, key):
        context.operation = key

    def equal(self, context):
        context.y = context.x
        context.state = StateAnswer()
        context.state.equal(context)

    def digit(self, context, key):
        context.state = StateY()
        context.state.digit(context, key)

    def clear(self, context):
        context.state = StateX()
        context.state.clear(context)


class StateX(State):
    def __init__(self):
        self._temping = False

    def arifm(self, context, key):
        context.state = StateAction()
        context.state.arifm(context, key)

    def equal(self, context):
        context.state = StateAnswer()
        context.state.equal(context)

    def digit(self, context, key):
        if not self._temping:
            context.x = 0
            self._temping = True
        context.x = context.x * 10 + int(key)

    def clear(self, context):
        context.x = 0
        context.y = 0
        context.operation = '+'


class StateY(State):
    def __init__(self):
        self._temping = False

    def arifm(self, context, key):
        self.equal(context)
        context.state.arifm(context, key)

    def equal(self, context):
        context.state = StateAnswer()
        context.state.equal(context)

    def digit(self, context, key):
        if not self._temping:
            context.y = 0
            self._temping = True
        context.y = context.y * 10 + int(key)

    def clear(self, context):
        context.state = StateX()
        context.state.clear(context)
