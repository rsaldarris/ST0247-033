import java.util.ArrayList;
import java.util.Stack;
/**
 * Write a description of class ShortWayProf here.
 *
 * @author Carlos Mesa,  Ricardo Saldarriaga, Benjamin De La Torre.
 * @version 24/03/19
 */
public class ShortWayProf {    
    public static ArrayList<Integer> ThereWayProf(Digraph g, int end, int start) {
        boolean[] visit = new boolean[g.size()];
        Stack<Integer> pile = new Stack<Integer>();
        ArrayList<Integer> way = new ArrayList<Integer>();
        ThereWayProf(g,end,start,visit, pile);
        way.add(start);
        while( !pile.isEmpty() ) {
            way.add( pile.pop() );
        }
        return way;
    }
    
       public static boolean ThereWayProf(Digraph g, boolean[] visit, int objective, int node, Stack<Integer> c) {
        ArrayList<Integer> successors = g.getSuccessors(node);
        visit[node] = true;
        if( successors != null ) {
            for( Integer successor : successors ) {              
                if( (objective || ThereWayProf(g, visit, objective, successor, c) == successor) && !visit[successor] ) {      
                    c.push( successor );
                    return true;
                }
            }
        }
        else {
          return false;
        }
    }
}
