/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dudas;

import java.util.ArrayList;
import java.util.Scanner;
import javafx.util.Pair;

/**
 *
 * @author Usuario
 */
public class Dudas {
  static Scanner sc = new Scanner(System.in);
  static int noReinas; 
  static ArrayList<Pair<Integer,Integer>> tableroMalo=new ArrayList<>();
  static int numTableros=0;
  
  public static void main(String[] args) {
    noReinas=sc.nextInt();
    while(noReinas>15||(noReinas<=2&&noReinas>0)||noReinas<=-1){
          noReinas=sc.nextInt();
    }
    
    while(noReinas != 0){
      System.out.println("No Reinas: "+noReinas);
      for(int fila=0;fila<noReinas;fila++){
        String lol=sc.nextLine();
        while(lol.length()!=noReinas){
           lol=sc.nextLine(); 
        }
        int columna=lol.indexOf("*");
        while(columna>-1){
          tableroMalo.add(new Pair<>(columna+1,fila+1));
          columna=lol.indexOf("*", columna+1);
        }
      }
      System.out.println("FIN");
      
      for(int m=0;m<tableroMalo.size();m++){
        System.out.println(tableroMalo.get(m).getKey()+","+tableroMalo.get(m).getValue());
      }
      queens(noReinas, tableroMalo);
      tableroMalo.clear();
      noReinas=sc.nextInt();
      while(noReinas>15||(noReinas<=2&&noReinas>0)||noReinas<=-1){
          noReinas=sc.nextInt();
      }
    }    
    
  }

  public static void queens(int n,ArrayList<Pair<Integer,Integer>> s) {
       numTableros=0;
       queensAux(n,0, new int[n],numTableros,s);
       System.out.println(numTableros);
    }
  
  private static void queensAux(int n, int columna, int[] tablero,int num,ArrayList<Pair<Integer,Integer>> s) {
     if (columna == n) {
        numTableros++;
//        imprimirTablero(tablero);
//        for(int j=0;j<tablero.length;j++){
//             System.out.print("("+(j+1)+","+(tablero[j]+1)+")");
//         }
//        System.out.println();
     } else {
       for(int i = 0;i<tablero.length;i++){
         tablero[columna] = i;
         boolean correcto=true;
         for(int m=0;m<s.size();m++){
             if(columna==s.get(m).getKey()&&i==s.get(m).getValue()){
                 correcto=false;
             }
         }
         if (esValidoBK(tablero,columna)&&correcto){
           
            queensAux(n, columna+1, tablero,num,s); 
         }
       }	     
     }   
}
  
  public static boolean esValidoBK(int[] tablero, int hastaColumnaI) {
	for (int i = 0; i <= hastaColumnaI; i++){
          for (int j = i+1; j <= hastaColumnaI ; j++){
             //System.out.print("("+(i+1)+","+(tablero[i]+1)+")-"+"("+(j+1)+","+(tablero[j]+1+")  "));
             if ( (Math.abs((tablero[i]+1) - (tablero[j]+1)) == Math.abs((i+1) - (j+1) ))||tablero[i] == tablero[j]){                    
                    return false;
                }
            }
        }
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
    
