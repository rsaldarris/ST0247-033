import java.util.ArrayList;
/**
 * Write a description of class x here.
 *
 * @author Mauricio Toro, Camilo Paez, Ricardo Saldarriaga, Carlos Daniel Mesa, Benjamin de la Torre
 * @version (a version number or a date)
 */
public class Punto2
{
     /**
     * Metodo que recorre un grafo por dfs
     * 
     * @param  g grafo ya implementado
     * @param  inicio nodo inicial
     * @param  fin nodo final
     * @return una lista con el camino encontrado
     */	
     public static ArrayList<Integer> camino(Digraph g, int PuntoPartida, int PuntoLlegada) {
	boolean[] visitados = new boolean[g.size()];
        ArrayList<Integer> lista = new ArrayList<>();
        if (dfs(g,PuntoPartida,PuntoLlegada,visitados,lista)){
            return lista;
        }
        return new ArrayList<Integer>();
     }

     /**
     * Metodo que implemeta un recorrido de un grafo por profundidad (dfs)
     * 
     * @param  g grafo ya implementado
     * @param  nodo nodo desde el cual se empezara hacer la busqueda
     * @param  objetivo hasta donde se quiere llegar
     * @param  visitados representa los nodos que ya se verificaron
     * @param  list la lista con el camino encontrados
     * @return  una lista con el camino
     */	
     private static boolean dfs(Digraph g, int nodo, int objetivo, boolean[] visitados, ArrayList<Integer> lista) {
        if(objetivo == nodo){
         lista.add(nodo);   
         return true;
        }
        else
        {
            ArrayList<Integer> successors = g.getSuccessors(nodo);
            visitados[nodo] = true;
            if(successors != null){
                for(int successor: successors){
                    if(!visitados[successor]){
                       lista.add(nodo);
                       return dfs(g,successor,objetivo,visitados,lista); 
                    } 
                }
                lista.remove(lista.size());
            }
        }

        return false;
     }
}
