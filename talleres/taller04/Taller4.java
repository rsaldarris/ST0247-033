import java.util.ArrayList;

/**
 * Clase en la cual se implementan los metodos del Taller 4
 * 
 * @author Mauricio Toro, Andres Paez
 */
public class Taller4 {

	/**
	* Metodo auxiliar para llamar el metodo hayCaminoDFS posterior
	* @param g grafo dado 
	* @param v vertices 
	* @param w vertice
	* @return true si hay camino, false de lo contrario
	*/
    public static boolean hayCaminoDFS(Digraph g, int v, int w) {
        boolean[] visitados = new boolean[g.size()];
        return hayCaminoDFS(g, v, w, visitados);
    }

    /**
	* Metodo que recorre el grafo por medio de dfs 
	* @param g grafo dado 
	* @param v vertices 
	* @param w vertice
	* @param visitados ayuda a tener un conteo acerca de que nodos han sido
	* o no visitados
	* @return true si hay camino, false de lo contrario
	*/
  private static boolean hayCaminoDFS(Digraph g, int v, int w, boolean[] visitados) {
          visitados[v] = true;
          if (v == w)
             return true;
          else{
              ArrayList<Integer> adyacentes = g.getSucessors(v);
              for (Integer adyacente: adyacentes){
                if (!visitados[adyacente]){
                     boolean respuesta = hayCaminoDFS(g, adyacente, w, visitados);
                     if (respuesta)
                      return respuesta
                  }
              }
              return false;   
            }
    }

    /**
	* Metodo que recorre el grafo por medio de dfs teniendo en cuenta que
	* se quiere encontrar el de menor costo
	* @param g grafo dado 
	* @param inicio nodo desde el cual empieza el recorrido 
	* @param fin nodo donde termina el recorrido
	* @return cual es el costo que tiene ir desde inicio a fin
	*/
	public static int costoMinimo(Digraph g, int inicio, int fin) {
		int[] costo = new int[g.size()];
		recorrido(g, inicio, costo)
		return costo[fin];
	}
	
	
	 

	/**
	* Metodo que recorre todo el grafo con la intencion de buscar un
	* camino que represente el menor costo pasando por todos los vertices exactamente
	* una vez y vuelva al nodo inicial
	* @param g grafo dado 
	* @param v vertice inicial
	* @param unvisited arreglo de nodos aun no visitados
	* @return cual es el costo que tiene
	*/
	private static void recorrido(Digraph g, int y, int[] costo) {
		ArrayList<Integer> adyacentes = g.getSucessors(v);
		for(Integer adyacente: adyacentes){
			int cos=costo[y]+g.getWeigth(y,adyacente);
			if(costo[adyacente]>cos){
				costo[adyacente]=cos;
			}
			recorrido(g,adyacente,costo)
		}
	}


	/**
	* Metodo que dada un conjunto de costos en cada arco, encuentra el camino desde el nodo v
	* @param g grafo dado 
	* @param v vertice inicial
	* @param coso arreglo de valores que tiene de ir de un nodo a otro
	* 
	*/
	private static void dfs(Digraph g, int v, int[] costo) {
		
	}

}
