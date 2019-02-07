public static void queens(int n) {
		  return queensAux(n,0, new int[n]);
	}
  
  private static boolean queensAux(int n, int columna, int[] tablero) {
     if (columna == n) {
        imprimir(tablero);
        return true;
     }
     else {
       for(int =0;i<tablero.length;i++){
        	tablero[columna] = i;
         if esValidoBK(tablero, columna){
               break;
         }
       }
     }   
     queensAux(n, columna+1, tablero);
        // if esValidoBK(tablero, columna)
        //         queensAux(n, columna+1, tablero);
        // else
	}
  
  public static boolean esValidoBK(int[] tablero, int hastaColumnaI) {
		   for (int
            i = 0; i < hastaColumnaI; i++)
          for (int j = i+1; j < hastaColumnaI - 1; j++)
             if ( Math.abs(tablero[i] - tablero[j]) == Math.abs(i - j ))                 
                     return false;
      return true;   
	 }
