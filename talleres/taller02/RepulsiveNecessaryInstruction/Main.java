import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;

class Main {
  public static void main(String[] args) {
    final String secretKey = "MZygpewJsCpRacbd";
     
    String originalString = "Rosas son rojas";
    String encryptedString = AES.encrypt(originalString, secretKey) ;
    String decryptedString = AES.decrypt(encryptedString, secretKey) ;


    System.out.println("Cadena original: "+originalString);
    System.out.println("Cadena encriptada: "+encryptedString);
    System.out.println("Cadena desencriptada: "+decryptedString);
    desencriptar("abcd");
  }

  public static void desencriptar(String respuesta){
		desencriptarPermutacion("",respuesta);
	}
	
	public static void desencriptarPermutacion(String respuesta,String pregunta){
		if (pregunta.length() == 0) 
		    intentoDesencriptar(respuesta);
		else {
		   for (int i = 0; i < pregunta.length(); i++)
		     desencriptarPermutacion(respuesta+pregunta.charAt(i), pregunta.substring(0,i)+pregunta.substring(i+1));
		}
	}

  public static void intentoDesencriptar(String l){
    
     try{
        String secretKey="MZygpewJsCpR"+l;
         Path path = Paths.get("archivoEncriptado.txt");
         byte[] archivoEncriptado = Files.readAllBytes(path);
          String archivo = new String(archivoEncriptado);
          String decryptedString = AES.decrypt(archivo, secretKey);
          if(decryptedString != null){
          System.out.println("Archivo desencriptado:  "+decryptedString+" \n Llave Secreta: "+ secretKey);}
     } catch(Exception e)
        {
            //e.printStackTrace(System.out);
            //return  "";
        } 
        
  }
  }