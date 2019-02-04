/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laboratorio1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.StringTokenizer;
import javafx.util.Pair;

/**
 *
 * @author Ricardo Saldarriaga, Carlos Daniel Mesa, Benjamin de la Torre
 */
public class Laboratorio1 {
    static HashMap<Pair<Long, String> , LinkedList< Pair <Long,Double> > > mapa = new HashMap();
    static ArrayList <Pair <Long,String> > vertices = new ArrayList < > ();
    static ArrayList <Pair <Long,Pair <Long,Double>> > arcos = new ArrayList < > ();
    
    public static void ordenarMapa(){
        System.out.println();
        for(int i=0;i<vertices.size();i++){
            long llave=vertices.get(i).getKey();
            mapa.put(new Pair<>(llave,vertices.get(i).getValue()) , new LinkedList <>());  
            for(int j=0;j<arcos.size();j++){                
                if(vertices.get(i).getKey().equals(arcos.get(j).getKey())){
                    long key=arcos.get(j).getValue().getKey();
                    double value=arcos.get(j).getValue().getValue();
                    mapa.get(vertices.get(i)).add(new Pair<> (key,value));
                    
                }
            }
        }
        System.out.println(mapa.size());
        for(int i=0;i<vertices.size();i++){
            long llave=vertices.get(i).getKey();
            String valor=vertices.get(i).getValue();
            System.out.println(mapa.get(new Pair<> (llave,valor)));
        }        
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        leerArchivo();
        ordenarMapa();
    }
    
    public static void leerArchivo() {
        String cadena,numero;
        StringTokenizer t;
        int tabla=0,info=0;
        
        try  {
            BufferedReader b = new BufferedReader(new FileReader("medellin_colombia-grande.txt"));
            while((cadena = b.readLine())!=null) {
                t=new StringTokenizer(cadena," ");
                String nombre="";
                int num=0;
                long id=0,id2=0;
                double dis=0;
                outerloop:
                while(t.hasMoreTokens()){
                    numero=t.nextToken();
                    boolean s=isDouble(numero);
                    if(s){
                        switch(num){
                            case 0:
                                    id=Long.parseLong(numero);
                                    System.out.print(id+" ");    
                                break;
                            case 1:
                                if(info==1){
                                    id2=Long.parseLong(numero);
                                }
                                break;
                            case 2:
                                if(info==1){
                                    dis=Double.parseDouble(numero);
                                }
                                break;
                            case 3:
                                
                                break;
                        }
                        num++;
                    } else {
                        switch(tabla){
                            case 0:
                                System.out.print("Titulo Tabla");
                                tabla++;
                                break outerloop;
                            case 1:
                                if(numero.equals("Arcos.")){     
                                    tabla++;
                                    info++;
                                    System.out.println("Cambio tabla");
                                    break outerloop;
                                }                              
                            default:
                                nombre+=numero;
                                System.out.print(nombre);
                                break;
                        }
                    }
                }
                if(info==0){
                    vertices.add(new Pair <> (id,nombre));
                }
                if(info==1){
                    arcos.add(new Pair <> (id,new Pair <> (id2,dis)));
                }
                System.out.println();
            }
        } catch(IOException | NumberFormatException ex){
            System.out.println("Error");
        }
        
        vertices.remove(0);
        vertices.remove(vertices.size()-1);
        arcos.remove(0);
        arcos.remove(arcos.size()-1);
        System.out.println("Vertices");
        for(int i=0;i<vertices.size();i++){
            System.out.println(vertices.get(i).getKey()+"->"+vertices.get(i).getValue());
        }
        System.out.println("Arcos");
        for(int i=0;i<arcos.size();i++){
            System.out.println(arcos.get(i).getKey()+"->("+arcos.get(i).getValue().getKey()+","+arcos.get(i).getValue().getValue()+")");
        }
        
        
        
    }
     static boolean isDouble(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
     
     static boolean isLong(String str) {
        try {
            Long.parseLong(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
}
