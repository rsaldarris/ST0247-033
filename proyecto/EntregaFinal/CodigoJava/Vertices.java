    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoeda2;

/**
 *
 * @author Usuario
 */
/*Clase punto para poder guardar cada vertice con la informacion de su:
  id-Poscicion en X-Poscicion en Y
*/
class Vertices{
	double x,y,dis;
        int id;
	Vertices(int aid,double ax,double ay,double adis){
		id=aid;
		x=ax;
		y=ay;
                dis=adis;
	}

  public boolean contains(Vertices ver,double r){
  	double d = Math.pow((this.x-ver.x),2)+Math.pow((this.y-ver.y),2);
  	return d <= (r*r);
  }
  

}
