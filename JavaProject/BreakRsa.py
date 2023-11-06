#SUMA DE numeros impares recursivamente
#RECIBE CUALQUIER NUMERO ENTERO POSITIVO

def sumaImpares(n):
    if n ==1 :
        return  1
    elif n%2 == 0:
        return sumaImpares(n-1)
    else:
        return n + sumaImpares(n-2)
def sum(n):
    if n == 1:
        return 1
    else :
        return sum(n - 1) + 2*n - 1

#hallar el macximo valor de un array recursivamente

def maximo(array):
    if len(array) == 1:
        return array[0]
    else:
        if array[0] > maximo(array[1:]):
            return array[0]
        else:
            return maximo(array[1:])

print(maximo([1,2,3,4,111,5,6,7,8,9,]))
def max (numbers ):
    if len (numbers ) == 1:
        return numbers [0]
    elif len (numbers)==2:
        if numbers[0]>numbers[1]:
            return numbers[0]
        else:
            return numbers[1]	
    else:
        return max([numbers[0],max(numbers[1:])])
print(max([1,2,3,4,111,5,6,7,8,9,]))

#sucesion de fibonacci recursivamente

def fib(n):
    if n < 2:
        return n
    else:
        return fib(n-1)+fib(n-2)
    
#busqueda binaria recursiva

def busquedaBinaria(array,elemento):
    if len(array) == 1:
        return array[0] == elemento
    else:
        medio = len(array)//2
        if array[medio] == elemento:
            return True
        elif array[medio] > elemento:
            return busquedaBinaria(array[:medio],elemento)
        else:
            return busquedaBinaria(array[medio:],elemento)
print(busquedaBinaria([1,2,3,4,5,6,7,8,9,10],10))
print(fib(10))