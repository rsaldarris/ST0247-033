ublic class Reinas {

    
    static int casillas = 4;
    static int[] tablero=new int[4];
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //permutacionesAux("","1230");
       queens(4);
    }
    
    public static void permutacionesAux(String respuesta, String pregunta){
        if (validarTablero(respuesta)==true){
            if (pregunta.length() == 0) 
                System.out.println("ESTA ESTA BIEN: " + respuesta);
            else {
               for (int i = 0; i < pregunta.length(); i++)
                 permutacionesAux(respuesta+pregunta.charAt(i), pregunta.substring(0,i)+pregunta.substring(i+1));
            } 
        }
        System.out.println(respuesta);
    }

    private static boolean validarTablero(String respuesta) {
        String numero=respuesta;
        ArrayList<Integer> numeros = new ArrayList();
        for(int i=0;i<numero.length();i++){
            String num = ""+numero.charAt(i);
            numeros.add(Integer.parseInt(num));
        }
        for(int i=0;i<numeros.size();i++){
            for(int j=i+1;j<numeros.size();j++){
                if(Math.abs((numeros.get(i)+1) - (i+1)) == Math.abs((numeros.get(j)+1)-(j+1))){
                    return false;
                }
            }
        }
        return true;
    }
   
    public static void queens(int n) {
        queensAux(n,0, new int[n]);
    }
  
  private static void queensAux(int n, int columna, int[] tablero) {
     if (columna == n) {
        imprimirTablero(tablero);
        //return true;
     } else {
       for(int i = 0;i<tablero.length;i++){
         tablero[columna] = i;
         if (esValidoBK(tablero, columna)){
		queensAux(n, columna+1, tablero);
               break;
         }
       }	     
     }   
}
  
  public static boolean esValidoBK(int[] tablero, int hastaColumnaI) {
	for (int i = 0; i <= hastaColumnaI; i++)
          for (int j = i+1; j <= hastaColumnaI ; j++)
             if ( Math.abs((tablero[i]+1) - (i+1)) == Math.abs((tablero[j]+1) - (j+1) ))                 
                     return false;
      return true;   
	 }

    
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
