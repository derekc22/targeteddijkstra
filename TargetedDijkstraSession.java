package _0_Algorithms.Graph_Traversal_Algorithms.TargetedDijkstra;
import _0_DataStructures.Graph.DirectedGraph;
import _0_DataStructures.Graph.DirectedGraph.GraphNode;
import _0_DataStructures.Graph.GraphGenerator;


public class TargetedDijkstraSession {

    public static DirectedGraph generateExampleGraph(){
        DirectedGraph g = new DirectedGraph();

        GraphNode n0 = g.addGraphNode(); // 0
        GraphNode n1 = g.addGraphNode(); // 1
        GraphNode n2 = g.addGraphNode(); // 2
        GraphNode n3 = g.addGraphNode(); // 3
        GraphNode n4 = g.addGraphNode(); // 4
        GraphNode n5 = g.addGraphNode(); // 5
        GraphNode n6 = g.addGraphNode(); // 6


        // 0 edges
        n0.addEdge(n1, 2, 1);
        n0.addEdge(n2, 1, 1);

        // 1 edges
        n1.addEdge( n2, 5, 1);
        n1.addEdge( n3, 11, 1);
        n1.addEdge( n4, 3, 1);

        // 2 edges
        n2.addEdge(n5, 15, 1);

        // 3 edges
        n3.addEdge(n4, 2, 1);

        // 4 edges
        n4.addEdge(n2, 1, 1);
        n4.addEdge(n5,4, 1);
        n4.addEdge(n6, 5, 1);

        // 5 edges
        // NONE

        // 6 edges
        n6.addEdge( n3, 1, 1);
        n6.addEdge( n5, 1, 1);


        g.printGraph();
        return g;
    }




    public static void main(String[] args) {

        TargetedDijkstraAlgorithm foo = new TargetedDijkstraAlgorithm();
        DirectedGraph graph1 = generateExampleGraph();
        foo.targetedDijkstra(graph1, 0, 6);
        System.out.println();


        GraphGenerator graphGen = new GraphGenerator(100, 2147483647, 1000);
        DirectedGraph graph2 = graphGen.genDirectedGraph();
        TargetedDijkstraAlgorithm bar = new TargetedDijkstraAlgorithm();
        bar.targetedDijkstra(graph2, 0, 29);









    }


}
