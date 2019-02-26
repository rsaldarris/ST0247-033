
 public static boolean hayCaminoDFS(Digraph g, int v) {
        boolean[] visitados = new boolean[g.size()];
        return hayCamino(g, v, visitados,e);
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
      return true;
    }
    if(visitado[proxVer]==false && proxVer != v){
      visitado[proxVer]=true;
      correcto=hayCamino(g,v,visitado,proxVer);
      if(!correcto){
        visitado[proxVer]=false;
      }
    }
  }
  if(correcto){
    return true;
  }
  return false;
  
}
