
 static int costoMenor=2147483647;
 static int cosMen=0;
 
 public static int hayCaminoDFS(Digraph g, int v) {
        boolean[] visitados = new boolean[g.size()];
        hayCamino(g, v, visitados,e);
        return costoMenor;
 }

private static boolean hayCamino(Diagraph g, int v,boolean[] visitado, int e){
  boolean fin = true;
  boolean correcto=false;
  
  ArrayList<Integer> proximos=g.getSuccesors(e);
  for(int i=0;i<visitado.length();i++){
    if(visitado[i]==false && i != v){
      fin=false;
      break;
    }
  } 
  
  for(int i=0;i<proximos.length();i++){
    int proxVer=proximos.get(i);
    if(proxVer==v && fin){
      if(cosMen<costoMenor){
       costoMenor=cosMen;
      }
      return true;
    }
    if(visitado[proxVer]==false && proxVer != v){
      int costo=g.getWeigth(v,proxVer);
      cosMen+=costo;
      visitado[proxVer]=true;
      correcto=hayCamino(g,v,visitado,proxVer);
      if(!correcto){
        visitado[proxVer]=false;
        cosMen-=costo;
      }
    }
  }
 
  return false;
  
}
