#:Author:Benjamin De La Torre, Ricardo Saldarriaga. Con ayuda de Santiago Espinosa, Juan Camilo
from Bicolorable import *

print()
print("Numero de nodos: ")
print()

size = int(input())

Graph = Bicolorable(size)

print()
print("Numero de arcos: ")
print()

arcs = int(input())

for i in range(arcs):

	print()
	print("Source: ")
	print()

	source = int(input())

	print()
	print("Destination: ")
	print()

	destination = int(input())

	Graph.addArc(source, destination, 1)

test = Graph.evaluate()

print()
print("Matriz resultante")
Graph.printMatriz()
print()

if test == True:

	print("BICOLORABLE")

else:

	print("NOT BICOLORABLE")
