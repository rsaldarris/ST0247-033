import java.util.ArrayList;

/**
 * Clase en la cual se implementan los metodos del Taller 5
 * 
 * @author Mauricio Toro, Camilo Paez
 */
public class Taller5 {


	/**
	* Metodo que dado un grafo y un numero m, se asigna un color
	* a cada nodo, de manera que dos nodos adyacentes no poseean el mismo color
	* @param g grafo dado 
	* @param m numero de colores
	* @return true si es posible, false de lo contrario
	*/
	public static boolean mColoring(Digraph g, int m) {
	int nodos=g.size;
	int[] colors = new int[nodos];
	posibleColor(g,m);
	boolean sePuede=mColoring(g,0,colors,m);
	}
	/**
	* Metodo que dado un grafo y un vertice v, intenta asignar un color
	* al nodo, de manera que dos nodos adyacentes no poseean el mismo color
	* @param g grafo dado 
	* @param m numero de colores
	* @param v vertice 
	* @param colors conjunto de colores
	* @return true si es posible, false de lo contrario
	*/
	private static boolean mColoring(Digraph g, int v, int[] colors, int m) {
		int ayuda=0;
		ArrayList<Integer> vecinos = g.getSucessors(v);
		for(int s=0;s<vecinos.size;s++){
			int veci=vecinos.get(s);
			for(int i=1;i<=m;i++){
				if(colors[veci]==0){
					if(siguientePuede(g,veci,i,colors)){
						colors[vecir]=i;
						if(!mColoring(g,veci,colors,m)){
							colors[vecir]=0;
						}
					}
				}else{
					ayuda++;
				}
			}
		}
		if(ayuda==vecinos.size){
			int veci=vecinos.get(0);
			mColoring(g,veci,colors,m);	
		}
		int numColors=0;
		for(int s=0;s<colors.length;s++){
			if(colors[s]==0){
				break;
			}
			numColors++;
		}
		if(numColors==colors.length){
			return true;
		}
		return false;
	}
	
	private static boolean siguientePuede(Diagraph g,int v, int color,int[] colors){
		ArrayList<Integer> proximos=g.getSucessors(v);
		for(int s=0;s<proximos.size;s++){
			if(colors[proximos.get(s)]==color){
				return false;
			}
		}
		return true;
	}
	

}
