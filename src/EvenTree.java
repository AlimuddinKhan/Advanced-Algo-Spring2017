import java.util.ArrayList;
import java.util.Scanner;

/**
 * Problem Statement: "Even Tree"
 *
 * @author Alimuddin Khan (aak5031@rit.edu)
 * @version on 3/23/17 at 2:47 PM
 */

// undirected graph
class Graph{
    // adjacency list array
    ArrayList<Integer>[] adjLists;

    // number of vertices in the graph
    int V;

    // create a graph of V vertices
    public Graph(int V) {
        this.V = V;
        this.adjLists = new ArrayList[V];

        // initialize all the adjacency list
        for (int i = 0; i < adjLists.length;i++){
            adjLists[i] = new ArrayList();
        }

    }

    /**
     * This method adds and edge between two vertices
     * of an "undirected graph"
     *
     *
     * @param src
     * @param dst
     */
    public void addEdge(int src, int dst){
        if (src < 0 || src > this.V - 1 || dst < 0
                || dst > this.V - 1){
            System.out.println("ERROR: Invalid edge");
            return;
        }

        // add src->dst
        adjLists[src].add(dst);


        // add dst -> src For directed graphs you have to
        // remove this line
         adjLists[dst].add(src);
    }

    @Override
    public String toString() {
        String graph = "Graph\n";
        graph += String.
                format("%4s : %s", "V", "Adjacency List\n");
        for (int i = 0; i < V; i++){
            graph += String.format("%4d : ", i+1);
            for (Integer nbr: adjLists[i]){
                graph += String.format("%4d ", nbr+1);
            }
            graph += "\n";
        }

        return graph;
    }
}
public class EvenTree {
    static int maxNumberOfEdges = 0;


    public static int modifiedDFS(Graph g,
                                  boolean[] hasVisited,
                                  int vertexNumber){

//        System.out.println("Visiting : " + vertexNumber);
        int numberOfVertices = 0;
        int tmp = 0 ;

        // mark as visited
        hasVisited[vertexNumber] = true;
//        MyFunctions.print(hasVisited);
//        System.out.println();

        // array list to store adj list of the current vertex
        ArrayList<Integer> cuurentAdjList=
                g.adjLists[vertexNumber];

        int nbr;

        for (int i = 0; i < cuurentAdjList.size(); i++){
            nbr = cuurentAdjList.get(i);
            if (hasVisited[nbr] == false){

                // get the number of vertices in the edge
                tmp = modifiedDFS(g, hasVisited,nbr);

//                System.out.println("got tmp as : " + tmp);
                if (tmp % 2 ==  0){
                    maxNumberOfEdges++;

                }else{
                    numberOfVertices += tmp;
                }
            }
        }

        return numberOfVertices + 1;
    }


    public static int getMaxNumberOfEdges(Graph g){
        boolean[] hasVisited = new boolean[g.V];
        modifiedDFS(g, hasVisited, 0);
        return maxNumberOfEdges;
    }

    public static void main(String[] args) {
//        System.out.println("Here");
        Scanner scanner = new Scanner(System.in);

        // get the number of vertices
        int V = scanner.nextInt();

        // get the number of edges
        int E = scanner.nextInt();

//        System.out.println("V:" +V + "\nE :" + E);

        // initialize the graph
        Graph graph = new Graph(V);

//        System.out.println(graph);

        // edge source and destinations
        int src, dst;
        for (int i = 0; i < E; i++){
            src = scanner.nextInt();
            dst = scanner.nextInt();

//            System.out.println("(src , dst)
// : (" +src + "," + dst + ")");
            graph.addEdge(src - 1, dst - 1);
        }

//        System.out.println(graph);

        System.out.println(getMaxNumberOfEdges(graph));
        scanner.close();
    }
}
