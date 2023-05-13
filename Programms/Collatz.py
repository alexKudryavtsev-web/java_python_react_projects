n = int(input('Введите число: '))

count = 0

while n > 1:
    if n % 2 == 0:
        n /= 2
    else:
        n = 3*n+1
    count += 1
    print('{0}'.format(n), end=' ')

print('\n' + str(count))
