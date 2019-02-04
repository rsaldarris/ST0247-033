#:Author: Benjamin De La Torre, Ricardo Saldarriaga, Mauricio. Con ayuda de Santiago Espinosa, Juan Camilo.
from GraphAM import *
	
class Bicolorable(GraphAM):
	
	"""
	Hereda de la clase GraphAM
	:see: https://github.com/ben61824/ST0247-Eafit/blob/master/laboratorios/lab01/codigo_estudiante/ENTREGA%20LAB%201/Punto%202/GraphAM.py
	Esta clase es un evaluador de grafos
	identifica si un grafo determinado
	puede ser pintado con dos colores
	
	El metodo para determinar si es Bicolorable o no
	se basa en la teoria de grafos, con los grafos bipartitos
	La clase a travez de una lista saca los sucesores de cada nodo
	y revisa por cada sucesor que los sucesores de el mismo
	no esten en los sucesores del nodo inicial
	:example: 
		Sea g un Graph Not Bicolorable
		con los nodos {0, 1, 2}
		y dados los arcos: 0--1
						   0--2
						   1--2
		El metodo evaluate determina que no es bipartito
		debido a que los sucesores de 0 son [1,2] y los
		sucesores de 1 son [0,2] entonces como 2 es sucesor
		de 1 y tambien de 0 y a demas 1 es sucesor de 0
		entonces el grafo no cumple la propiedad de bipartito
	"""

	def __init__(self, size):

		"""
		Constructor
		:param size: numero de nodos del grafo 
		"""
		super().__init__(size)
		self.valuate = True #Determina si el grafo es Bicolorable
		self.successors = [] #Succesores de un nodo
		self.sucessorsAux = [] #Sucesores de los succesores del nodo


	def evaluate(self):

		"""
		:see: documentacion de la clase
		"""

		matriz = self.getMatriz()

		for i in self.getMatriz():

			self.sucessors = self.getSuccessors(self.getNode(i))

			for j in self.sucessors:

				self.sucessorsAux = self.getSuccessors(j)

				for y in self.sucessorsAux:
					for z in self.sucessors:
						
						if y == z:

							self.valuate = False					


		return self.valuate


if __name__ == "__main__":

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
