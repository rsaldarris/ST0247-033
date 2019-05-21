/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoeda2;

import java.io.BufferedReader;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Collections;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
/**
 *  Este tiene un cambio en la forma del HashMap, cambiando de String a un Double 
 * por lo que se debe cambiar el id de un int a un double
 * @author Usuario
 */
public class ProyectoEdA2 {
  
 
  
  //Este HashMap contiene en el String los dos vertices creados con la ayuda del metodo llave() y en Integer es el camino entre los dos vertices
  static HashMap<Double,Integer> caminos = new HashMap<>();
  //Este ArrayList contiene todos los vertices
  static ArrayList<Vertices> vertices = new ArrayList<Vertices>();
  
  public static void main(String[] args) {
    long TInicioleer, TFin,TfinTot, tiempo; //Variables para determinar el tiempo de ejecución
    TInicioleer = System.currentTimeMillis();
    readFile("index.txt");
    TFin = System.currentTimeMillis();
    tiempo = TFin - TInicioleer;
    
     System.out.println("Tiempo de ejecución en milisegundos para leer: " + tiempo);
    
    JFrame ventana= new JFrame();
    ventana.setTitle("Proyecto Datos 2");
    ventana.setVisible(true);
    
    //Se divide el mapa en 4 cuadrantes
    ArrayList<ArrayList<Vertices>> division = definirVertices(vertices);
    
    //se divide el primer cuadrante por radios
    ArrayList<ArrayList<Vertices>> arribaDch = new ArrayList<>();
    ArrayList<ArrayList<Vertices>> arribaIzq = new ArrayList<>();
    ArrayList<ArrayList<Vertices>> abajoDch = new ArrayList<>();
    ArrayList<ArrayList<Vertices>> abajoIzq = new ArrayList<>();

    System.out.println("definirVerOK AD = "+division.get(0).size()+" AI = "+division.get(1).size()
            +" BI = "+division.get(2).size()+" BD = "+division.get(3).size());
    
    if(!division.get(0).isEmpty()){
       arribaDch = definirRadio(division.get(0));
    }
    //se divide el segundo cuadrante por radios
    if(!division.get(1).isEmpty()){
        arribaIzq = definirRadio(division.get(1));
    }
    //se divide el cuarto cuadrante por radios
    if(!division.get(3).isEmpty()){
        abajoDch = definirRadio(division.get(3));
    }
    //se divide el tercer cuadrante por radios
    if(!division.get(2).isEmpty()){
        abajoIzq = definirRadio(division.get(2));
    }
    
    ArrayList<ArrayList<Integer>> aD= new ArrayList<>();
    ArrayList<ArrayList<Integer>> aI= new ArrayList<>();
    ArrayList<ArrayList<Integer>> bD= new ArrayList<>();
    ArrayList<ArrayList<Integer>> bI= new ArrayList<>();

    //System.out.println("3");
    if(!(arribaIzq.isEmpty())){
        //System.out.println("entroAI");
        aI = crearRuta(arribaIzq);    
        //System.out.println("AI "+aI.size());
    }
    if(!(arribaDch.isEmpty())){
        //System.out.println("entroAD"); 
        aD =crearRuta(arribaDch);
        //System.out.println("AD "+aD.size());        
    }
    if(!(abajoDch.isEmpty())){
        //System.out.println("entroBD");
        bD =crearRuta(abajoDch);
        //System.out.println("BD "+bD.size());
    }
    if(!(abajoIzq.isEmpty())){
        //System.out.println("entroBI");
        bI = crearRuta(abajoIzq);
        //System.out.println("BI "+bI.size());
    }
    int total=aD.size()+aI.size()+bD.size()+bI.size();
    //System.out.println("BI = "+bI.get(0).isEmpty());
    
    ventana.jLabel2.setText(vertices.size()+" vertices");
    ventana.jLabel4.setText(total+" carros");
    System.out.println("Numero de carros usados: "+total);
    
    TfinTot = System.currentTimeMillis();
    tiempo = TfinTot-TFin;
    
     System.out.println("Tiempo de ejecución en milisegundos para ejecutar: " + tiempo);
    
  }
  
  
  public static void readFile(String s) {
    final String fileName;
      fileName = s;
    String line;
    String empiezaVertices = "Vertices. Formato: ID, coordenada x, coordenada y, nombre";
    String empiezaCaminos = "Costo de Caminos Cortos. Formato: ID, ID, peso";
    BufferedReader bufferedReader;
    Scanner sc = new Scanner(System.in);
    try {
      	bufferedReader = new BufferedReader(new FileReader(new File(fileName)));
        sc = new Scanner(bufferedReader);
        //System.out.println("Entro");
    } catch ( FileNotFoundException e ) {
      	System.out.println(e);
        System.exit(-1);
    }
    while (sc.hasNextLine()){
       	line = sc.nextLine();
     	if (line.equals(empiezaVertices)){
            //System.out.println("Vertices");
            sc.nextLine();
            leerVertice(sc);
        }
	if (line.equals(empiezaCaminos)){
            //System.out.println("Caminos");
            sc.nextLine();
            leerCamino(sc);
        }
    }
  }
    
	//este metodo lee todos los vertices y llama al metodo creador de vertices para cada una de las lecturas.
    private static void leerVertice(Scanner sc){
        
  	String line;
        int id;
        double x;
        double y;
        double dis = 0;
        line = sc.nextLine();
        while( line.length() > 0 ){           
            String[] str = line.split(" ");
            id = Integer.parseInt(str[0]);
            x = Double.parseDouble(str[1]);
            y = Double.parseDouble(str[2]);
            if (id == 1){
                añadirVertice(id, x, y, dis);
            } else {
                dis = Math.sqrt(Math.pow((vertices.get(0).x - x),2)+Math.pow((vertices.get(0).y - y),2));
                añadirVertice(id, x, y, dis);
            }
            line = sc.nextLine();
        }
        
    }
    
	//este metodo lee todos los caminos y llama al metodo creador de caminos para cada una de las lecturas.
  private static void leerCamino(Scanner sc){
    String line;
    int id1;
    int id2;
    int peso;
    line = sc.nextLine();
    while( line.length() > 0 ){
      String[] str = line.split(" ");
      id1 = Integer.parseInt(str[0]);
      id2 = Integer.parseInt(str[1]);
      peso = Integer.parseInt(str[2]);
      //System.out.println("ID1= "+id1);
      añadirCamino(id1, id2, peso);
      if(sc.hasNext()){
        line = sc.nextLine();
      }else{
          break;
      }
    }
    
  }
    	
  //este metodo añade un vertice con las variables entregadas por el medtodo leerVertice
  private static void añadirVertice(int id,double x,double y, double dis){
  	vertices.add(new Vertices(id, x, y, dis));
  }
    
  //este metodo añade un camino con las variables entregadas por el medtodo leerCamino
  private static void añadirCamino(int id1,int id2,int peso){
  	if(id1 != id2){
            caminos.put(llave(id1,id2),peso);   
        }
  }

  /*
    Metodo que crea las llaves del HashMap de los caminos entre los vertices
    Retornando de esta manera: "IDVerticeMenor;IDVerticeMayor"
  */
  static Double llave(double a,double b){
    b=b/1000;
    return a+b;
  }

  /*
   * Metodo que retorna en un ArrayList los caminos del vertice con ID "a"
   * Es decir que en el ArrayList.get(#).getCamino() va a retornar el camino de "a" hasta ese #
   * 
   * ArrayList de Camino para saber a que camino va y su distancia, este sirve para poder hacer un sort (organizar ascendente o descendentemente)
   * @param int 'a' es el ID del vertice al cual se va a ver la distancia de ese a a cada vertice del ArrayList 'b'
   * @param ArrayList<Vertices> 'b' es el arreglo de vertices a los que se va a ver el camino de 'a' a cada uno de los elementos de 'b'
   * @return ArrayList<Camino> los caminos de el id de 'a' hasta cada uno de los vertices de el ArrayList 'b'
   */
  static ArrayList<Camino> crearCamino(int a,ArrayList<Vertices> b){
    int len = b.size();
    ArrayList<Camino> dis = new ArrayList<>();
    for(int i=0;i<len;i++){        
      Double lave = llave(a,b.get(i).id);
      dis.add(new Camino(b.get(i).id,caminos.get(lave)));      
    }
    return dis;
  }

  /*
   * Metodo para ordenar ascendentemente el arreglo de caminos, segun los caminos 
   * Video: https://www.youtube.com/watch?v=wzWFQTLn8hI
   */
  static ArrayList<Camino> caminoAsc(ArrayList<Camino> a){
     Collections.sort(a, new Comparator<Camino>()
    {
      @Override
      public int compare(Camino c1,Camino c2)
      {
      return Integer.valueOf(c1.peso).compareTo(c2.peso);
      }
      });
    return a;
  }

  /* Metodo para ordenar desendentemente el arreglo de caminos, segun los caminos 
   * Video: https://www.youtube.com/watch?v=wzWFQTLn8hI
   */
  static ArrayList<Camino> caminoDes(ArrayList<Camino> a){
     Collections.sort(a,new Comparator<Camino>()
    {
      @Override
      public int compare(Camino c1,Camino c2)
      {
      return Integer.valueOf(c2.peso).compareTo(c1.peso);
      }
    });
    return a;
  }
  
  static ArrayList<Vertices> distanciaDes(ArrayList<Vertices> a){
     Collections.sort(a,new Comparator<Vertices>()
    {
      @Override
      public int compare(Vertices c1,Vertices c2)
      {
      return Double.valueOf(c2.dis).compareTo(c1.dis);
      }
    });
    return a;
  }

  /*
    Metodo que devuelve un ArrayList de 4 ArrayList de vertices, cada uno para un cuadrante.
    Orden de primer,segundo,cuarto,tercero
  */
  static ArrayList<ArrayList<Vertices>> definirVertices(ArrayList<Vertices> lista){

    ArrayList<Vertices> ad = new ArrayList<>();
    ArrayList<Vertices> ai = new ArrayList<>();
    ArrayList<Vertices> bd = new ArrayList<>();
    ArrayList<Vertices> bi = new ArrayList<>();
    
    ArrayList<ArrayList<Vertices>> cuadrantes = new ArrayList<>();

    for(int i=1;i<lista.size();i++){
      if(lista.get(0).x <= lista.get(i).x && lista.get(0).y <= lista.get(i).y){
        ad.add(lista.get(i));
      } 
      else  if(lista.get(0).x <= lista.get(i).x && lista.get(0).y >= lista.get(i).y){
        bd.add(lista.get(i));
      } 
      else  if(lista.get(0).x >= lista.get(i).x && lista.get(0).y <= lista.get(i).y){
        ai.add(lista.get(i));
      } 
      else  if(lista.get(0).x >= lista.get(i).x && lista.get(0).y >= lista.get(i).y){
        bi.add(lista.get(i));
      } 
    }
    cuadrantes.add(ad);
    cuadrantes.add(ai);
    cuadrantes.add(bd);
    cuadrantes.add(bi);
    return cuadrantes;
  }

  /*
   * Metodo que devuelve un ArrayList de 4 ArrayList de vertices, cada uno para un radio.
    Siendo el primer conjunto de elementos el que esta en el primer radio, el segundo conjunto de elementos el que esta en el segundo radio y asi
  */
  static ArrayList<ArrayList<Vertices>> definirRadio(ArrayList<Vertices> lista){
    ArrayList<ArrayList<Vertices>> radios = new ArrayList<>();
    
    ArrayList<Vertices> primer = new ArrayList<>();
    ArrayList<Vertices> segundo = new ArrayList<>();
    ArrayList<Vertices> tercer = new ArrayList<>();
    ArrayList<Vertices> cuarto = new ArrayList<>();
    
    //Se sacan todas las rutas desde eafit hasta los vertices
    ArrayList<Vertices> rutaEafit = lista;
    rutaEafit=distanciaDes(rutaEafit);
    
    double dist=rutaEafit.get(0).dis;    
    double r1=dist/4.0;
    double r2=(dist*2.0)/4.0;
    double r3=(dist*3.0)/4.0;
    //System.out.println("RADIO= "+dist+" r1: "+r1+" r2: "+r2+" r3: "+r3);
    
    for(int i=0;i<lista.size();i++){
      if(vertices.get(0).contains(lista.get(i), r1)){
        primer.add(lista.get(i));
      } else if(vertices.get(0).contains(lista.get(i), r2)){
        segundo.add(lista.get(i));
      } else if(vertices.get(0).contains(lista.get(i), r3)){
        tercer.add(lista.get(i));
      }else {
        cuarto.add(lista.get(i));
      }
    }
    radios.add(primer);
    radios.add(segundo);
    radios.add(tercer);
    radios.add(cuarto);

    return radios;
  }
  
  /*
    Este metodo crea la ruta desde el vertice mas alejado de EAFIT hasta EAFIT
  
    Siendo lo primero que se hace una ruta que pase por todos los radios sin ningun problema
    y despues un ciclo en el cual pueda recoger si se puede a una cantidad x (la cual depende de cuando se acaban los radios) 
    de otro radio
  
    @param ArrayList<ArrayList<Vertices>> este arreglo preaviamente limitado por radios y 
    por estar en un cuadrante es el que se le va a hacer la ruta
    
    @return ArrayList<ArrayList<Integer>> este arreglo tiene como elementos los id de los vertices por los que paso
  
  */
   
  static ArrayList<ArrayList<Integer>> crearRuta(ArrayList<ArrayList<Vertices>> ruta){
    ArrayList<ArrayList<Integer>> rutaOptima = new ArrayList<>();
    ArrayList<Integer> casas = new ArrayList<>();
    int recoger=1;
    
    int[] r = {ruta.get(0).size(),ruta.get(1).size(),ruta.get(2).size(),ruta.get(3).size()};
    int min=Math.min(r[0],r[1]);
    min=Math.min(min, r[2]);
    min=Math.min(min, r[3]);
    //System.out.println("MIN: "+min+" r[0] = "+r[0]+" r[1] = "+r[1]+" r[2] = "+r[2]+" r[3] = "+r[3]);

    
    for(int i=0;i<min;i++){
      int cod=ruta.get(3).get(0).id; 
      casas.add(cod);
      
      
      ruta.get(3).remove(0);
      
      
      for(int j=2;j>=0;j--){
          
        ArrayList<Camino> way = crearCamino(casas.get(casas.size()-1), ruta.get(j));
        way=caminoAsc(way);
        
        //Se le agrega el primero elemento de la lista
        int masCercano=way.get(0).id;
        casas.add(masCercano);
       
        //CODIGO QUE SE PODRIA MEJORAR
        boolean encontro=false;
        for(int k=0; k<ruta.get(j).size() && !encontro;k++){
          if(ruta.get(j).get(k).id == masCercano){
            ruta.get(j).remove(k);//Se quita el vertice
            encontro=true;
          } 
        }
      }
      
      
//      for(int xo=0;xo<casas.size();xo++){
//          System.out.print("En "+xo+" casas = "+casas.get(xo)+" -> ");
//      }
      
      //System.out.println();
      ArrayList personas = (ArrayList) casas.clone();
      rutaOptima.add(personas);
      casas.clear();
    }
    //System.out.println("\nDio perfecto");
    r[0]=r[0]-min;
    r[1]=r[1]-min;
    r[2]=r[2]-min;
    r[3]=r[3]-min;  
    
    for(int i=0;i<r.length;i++){
      if(r[i]==0){
        recoger++;
      }
    }
    while(r[0]!=0||r[1]!=0||r[2]!=0||r[3]!=0){
      int primer = 0;
      boolean recogerHecho=false;
      int aux=recoger;
      
      for(int x=3;x>=0;x--){
        if(r[x] != 0){
          if(primer == 0){
            casas.add(ruta.get(x).get(0).id);
            
            
            ruta.get(x).remove(0);
            primer++;
            r[x]=r[x]-1;
            
            
            if(r[x]>=aux-1 && !recogerHecho){
                recogerHecho=true;
                int agg=0;
                int m=aux-1;
                while(m!=agg){
                  agg++;
                  ArrayList<Camino> way = crearCamino(casas.get(casas.size()-1), ruta.get(x));
                  way=caminoAsc(way);
                  //Se le agrega el primero elemento de la lista

                  int masCercano=way.get(0).id;//Este es el id mas cercano a codigo
                  casas.add(masCercano);
                  
                  
                  boolean encontrar=false;
                  for(int k=0;k<=ruta.get(x).size() && !encontrar;k++){
                    if(ruta.get(x).get(k).id == masCercano){
                      ruta.get(x).remove(k);//Se quita el vertice
                      break;
                    } 
                  }
                  r[x]=r[x]-1;
                  if(r[x]==0){
                    recoger++;
                  }
                }
            }
          }else if(r[x] >= aux && !recogerHecho){
            
            recogerHecho=true;
            int agg=0;
            int m=aux;
            
            while(m!=agg){
              ArrayList<Camino> way = crearCamino(casas.get(casas.size()-1), ruta.get(x));
              way=caminoAsc(way);
              //Se le agrega el primero elemento de la lista
              
              int masCercano=way.get(0).id;//Este es el id mas cercano a codigo
              casas.add(masCercano);
              
              
              boolean encontrar=false;
              for(int k=0;k<=ruta.get(x).size()&&!encontrar;k++){
                if(ruta.get(x).get(k).id == masCercano){
                  ruta.get(x).remove(k);//Se quita el vertice
                  break;
                } 
              }
              r[x]=r[x]-1;
              agg++;
              if(r[x]==0){
                recoger++;
              }
            }
          } else {
            ArrayList<Camino> way = crearCamino(casas.get(casas.size()-1), ruta.get(x));
            way=caminoAsc(way);
            
            //Se le agrega el primero elemento de la lista
            int masCercano=way.get(0).id;//Este es el id mas cercano a codigo
            casas.add(masCercano);
            
            
            boolean encontrar=false;
            for(int k=0;k<=ruta.get(x).size()&& !encontrar;k++){
                if(ruta.get(x).get(k).id == masCercano){
                  ruta.get(x).remove(k);//Se quita el vertice
                  encontrar=true;
                } 
            }
            
            r[x]=r[x]-1;
            if(r[x]==0){
              recoger++;
            }
          }
          
        }
        
      }
      
//      for(int xo=0;xo<casas.size();xo++){
//          System.out.print("En "+xo+" casas = "+casas.get(xo)+" -> ");
//      }
//      System.out.println();
      ArrayList personas = (ArrayList) casas.clone();
      rutaOptima.add(personas);
      casas.clear();
    }
    //System.out.println(rutaOptima.get(0).get(0));
    return rutaOptima;
  }
  
}




