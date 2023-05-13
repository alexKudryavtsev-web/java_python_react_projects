def calc(r, pos, speeds):
    if len(pos) != len(speeds):
        raise ValueError()
    while True:
        for index, drop, speed in zip(range(0, len(pos)), pos, speeds):
            pos[index] = pos[index] + speed
            if drop >= r:
                return index
        print(pos)


print(calc(float('inf'), [1, 1, 1, 1], [20, 40, 1334, 2]))
