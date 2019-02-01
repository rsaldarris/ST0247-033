 import java.util.ArrayList;

/**
 * Clase en la cual se implementan los metodos del Taller 2
 * 
 * @author Mauricio Toro, Camilo Paez
 */
public class Taller2 {
	
	/**
	* Metodo auxiliar que llama al metodo combinations posterios
	* 
	* @param s es la cadena sobre la cual se haran las combinaciones
	
	*/	
	public static void subconjuntos(String s) {
        subconjuntosAux("",s);
    }

	/**
	* Metodo para obtener las posibles combinaciones que se pueden hacer
	* con los elementos dados
	* 
	* @param pregunta es la cadena sobre la cual se van a hacer las combinaciones
	* @param respuesta es la cadena en la cual van a estar las posibles combinaciones
	* 
	*/	
	public static void subconjuntosAux(String respuesta, String pregunta){
        if (pregunta.length() == 0)
            System.out.println(respuesta);
        else {
           subconjuntosAux(respuesta, pregunta.substring(1));
           subconjuntosAux(respuesta+pregunta.substring(0,1), pregunta.substring(1));
        } 
	}
	
	/**
	* Metodo que permite desencriptar el archivo archivoEncriptado.txt

	*/
	public static void desencriptar(String respuesta){
		desencriptarPermutacion("",respuesta);
	}
	
	/**
	* Metodo auxiliar para desencriptar el archivo encriptado con la ayuda de la clase AdvancedEncryptionStandard
	*
	* @param pregunta es la cadena sobre la cual se van a hacer las permutaciones
	* @param respuesta es la cadena en la cual van a estar las posibles permutaciones
	*/
	
	public static void desencriptarPermutacion(String respuesta,String pregunta){
		if (pregunta.length() == 0) 
		    AdvancedEncryptionStandard.desencriptarArchivo(respuesta);
		else {
		   for (int i = 0; i < pregunta.length(); i++)
		     permutacionesAux(respuesta+pregunta.charAt(i), pregunta.substring(0,i)+pregunta.substring(i+1));
		}
	}
	
	/**
	* Metodo auxiliar que llama al metodo permutations posterios
	* 
	* @param  s cadena sobre las cuales se van a hacer las permutaciones
	*/	
	public static void permutaciones(String s) {
        	permutacionesAux("",s);
    	}
	
	/**
	* Metodo para obtener las posibles permutaciones que se pueden hacer
	* con los caracteres de una cadena dada, recuerde que las letras no se 
	* repiten en este ejercicio
	* 
	* @param pregunta es la cadena sobre la cual se van a hacer las permutaciones
	* @param respuesta es la cadena en la cual van a estar las posibles permutaciones
	* 
	*/	
	public static void permutacionesAux(String respuesta, String pregunta){
		if (pregunta.length() == 0) 
		    System.out.println(respuesta);
		else {
		   for (int i = 0; i < pregunta.length(); i++)
		     permutacionesAux(respuesta+pregunta.charAt(i), pregunta.substring(0,i)+pregunta.substring(i+1));
		} 
    	}

	/**
	* Metodo que imprime en pantalla como esta el tablero dado
	* 
	* @param  tablero es un arreglo con las posiciones de un tablero
	* de ajedrez
	* no se modifica.
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

	/**
	* Metodo auxiliar verifica si el tablero implementado es correcto
	* es decir rodas las reinas se posicionan de foma correcta
	* 
	* @param  tablero un arreglo con las posiciones de un tablero de ajedrez
	* @return true si es verdaderos, false de lo contrario
	*/	
	public static boolean esValido(int[] tablero) {
		
	}

	/**
	* Metodo que muestra el numero de posibles soluciones al problema
	* 
	* 
	* @param  n numero de reinas
	* @return numero de soluciones
	*/	
	public static int queens(int n) {
		
	}
} 
