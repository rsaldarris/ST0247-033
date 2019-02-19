
/**
 * Implementacion de un grafo dirigido usando listas de adyacencia
 *
 * @author Mauricio Toro, Mateo Agudelo, Ricardo Saldarriaga, Benjamin de la Torre
 */
import java.util.ArrayList;
import java.util.LinkedList;
import javafx.util.Pair;
public class DigraphAL extends Digraph {
	
 LinkedList<LinkedList<Pair<Integer,Integer>>> lista; 
	/**
	* Constructor para el grafo dirigido
	* @param vertices el numero de vertices que tendra el grafo dirigido
	*
	*/
	public DigraphAL(int size) {
		super(size);
		lista = new LinkedList();
    		for (int i = 1; i <= size; i++)
       			lista.add(new LinkedList<Pair<Integer,Integer>>());
	}

	/**
	* Metodo para añadir un arco nuevo, donde se representa cada nodo con un entero
	* y se le asigna un peso a la longitud entre un nodo fuente y uno destino	
	* @param source desde donde se hara el arco
	* @param destination hacia donde va el arco
	* @param weight el peso de la longitud entre source y destination
	*/
	public void addArc(int source, int destination, int weight) {
		LinkedList<Pair<Integer,Integer>> listaSource = lista.get(source);
		listaSource.add(new Pair(destination,weight));
	}

	/**
	* Metodo para obtener una lista de hijos desde un nodo, es decir todos los nodos
	* asociados al nodo pasado como argumento
	* @param vertex nodo al cual se le busca los asociados o hijos
	* @return todos los asociados o hijos del nodo vertex, listados en una ArrayList
	* Para más información de las clases:
 	* @see <a href="https://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html"> Ver documentacion ArrayList </a>
	*/
	public ArrayList<Integer> getSuccessors(int vertex) {
		 // Una nueva lista que saque los sucesores
     		ArrayList<Integer> respuesta = new ArrayList();
     		LinkedList<Pair<Integer,Integer>> listaDeParejas = lista.get(vertex);
     		for (Pair<Integer,Integer> pareja: listaDeParejas)
       			respuesta.add(pareja.getKey()); //segunda parte de la pareja
     		return respuesta;
	}

	/**
	* Metodo para obtener el peso o longitud entre dos nodos
	* 
	* @param source desde donde inicia el arco
	* @param destination  donde termina el arco
	* @return un entero con dicho peso
	*/	
	public int getWeight(int source, int destination) {
	    // Un ciclo que dentro de la lista source, cuando
	    //encuentre que sea el destino correcto, retorne el peso
	    int weight = 0;
	    LinkedList<Pair<Integer,Integer>> listaDeParejas = lista.get(source);
	    for (Pair<Integer,Integer> pareja: listaDeParejas)
	    	if (pareja.getKey()==destination)
			weight = pareja.getValue();
	    return weight;

	}

}
