/**
 * Clase en la cual se implementan los metodos del Taller 3
 * 
 * @author Mauricio Toro, Camilo Paez,Ricardo Saldarriaga, Carlos Daniel Mesa,Benjamin de la Torre
 */
public class Reinas {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       queens(4);
    }
   
    /**
     * @param n el numero de reinas que va a tener el tablero
     */
    public static void queens(int n) {
        queensAux(n,0, new int[n],0);
    }
  
     /**
     * Este metodo sirve para poner las reinas dentro del tablero con la ayuda de otro metodo que valida el punto
     * @param n es el numero de reinas
     * @param columna es la columna actual en la que se esta trabajando
     * @param tablero es el tablero en el que se trabaja
     */
  private static void queensAux(int n, int columna, int[] tablero) {
     if (columna == n) {
        imprimirTablero(tablero);
        for(int j=0;j<tablero.length;j++){
             System.out.print("("+(j+1)+","+(tablero[j]+1)+")");
         }
        System.out.println();
     } else {
       for(int i = 0;i<tablero.length;i++){
         tablero[columna] = i;
         if (esValidoBK(tablero,columna)){
            queensAux(n, columna+1, tablero); 
         }
       }	     
     }   
}
  /**
     * Metodo que valida si el tablero esta bien usando backtracking
     * @param hastaColumnaI es la columna hasta la cual se va a validar para hacer efectivo el backtracking
     * @param tablero es el tablero en el que se trabaja
     */
  public static boolean esValidoBK(int[] tablero, int hastaColumnaI) {
	for (int i = 0; i <= hastaColumnaI; i++){
          for (int j = i+1; j <= hastaColumnaI ; j++){
             if ( (Math.abs((tablero[i]+1) - (tablero[j]+1)) == Math.abs((i+1) - (j+1) ))||tablero[i] == tablero[j]){                    
                    return false;
                }
            }
        }
      return true;   
    }

  /**
     * Metodo que imprime el tablero
     * @param tablero es el tablero en el que se trabaja
     */    
  public static void imprimirTablero(int[] tablero) {
		int n = tablero.length;
		System.out.print("    ");
		for (int i = 0; i < n; ++i)
			System.out.print(i + " ");
		System.out.println("\n");
		for (int i = 0; i < n; ++i) {
			System.out.print(i + "   ");
			for (int j = 0; j < n; ++j)
				System.out.print((tablero[i] == j ? "Q" : "#") + " ");
			System.out.println();
		}
		System.out.println();
	}  
   
  
}
