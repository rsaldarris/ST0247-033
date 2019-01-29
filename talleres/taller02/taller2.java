import java.util.ArrayList;

public class Taller2
{
    public static void subconjuntos(String s) {
        subconjuntosAux("",s);
    }
    
    public static void subconjuntosAux(String respuesta, String pregunta){
        if (pregunta.length() == 0)
            System.out.println(respuesta);
        else {
           subconjuntosAux(respuesta, pregunta.substring(1));
           subconjuntosAux(respuesta+pregunta.substring(0,1), pregunta.substring(1));
        } 
    }
  
      public static void permutaciones(String s) {
        permutacionesAux("",s);
    }
    // Meme https://www.facebook.com/estructurasdedatos1/photos/a.482871115405556/482871185405549/?type=3&theater
    public static void permutacionesAux(String respuesta, String pregunta){
        if (pregunta.length() == 0) 
            System.out.println(respuesta);
        else {
           for (int i = 0; i < pregunta.length(); i++)
             permutacionesAux(respuesta+pregunta.charAt(i), pregunta.substring(0,i)+pregunta.substring(i+1));
        } 
    }
}
