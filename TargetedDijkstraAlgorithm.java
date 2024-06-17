package _0_Algorithms.Graph_Traversal_Algorithms.TargetedDijkstra;
import _0_DataStructures.Graph.DirectedGraph;
import _0_DataStructures.Graph.DirectedGraph.GraphNode;
import java.util.ArrayList;
import java.util.LinkedHashMap;


public class TargetedDijkstraAlgorithm  {

    public DirectedGraph dijkstraGraph;
    public ArrayList<DijkstraNode> dijkstraNodeList = new ArrayList<>();
    public Fringe fringe;
    public DijkstraNode currentNode;
    public DijkstraNode originNode;
    public int originNodeNum;

    public int[] pathToArr;



    public void initAlgorithm(DirectedGraph graph, int originNodeNum){
        this.dijkstraGraph = graph;
        this.originNodeNum = originNodeNum;
    }


    public class DijkstraNode  {

        public int number;

        public int incumbentDistTo;
        public Integer edgeTo;

        public boolean isMarked; // set to 0 by default

        public LinkedHashMap<DijkstraNode, ArrayList<Integer>> connections;


        public void markNode(){
            this.isMarked = true;
        }

        public void updateHighlightedNode(int calculatedDistTo, int highlightedEdgeWeight){

            this.incumbentDistTo = calculatedDistTo;
            this.edgeTo = currentNode.number;
            pathToArr[this.number] = highlightedEdgeWeight;


            Integer fringeEntryIndex = null;
            for (Fringe.Node node : fringe.keys){
                if (node.value != null && node.number == this.number){
                    fringeEntryIndex = node.index;
                }
            }
            Fringe.Node highlightedNodeFringeEntry = fringe.getNode(fringeEntryIndex);
            highlightedNodeFringeEntry.value = calculatedDistTo;

            //highlightedNodeFringeEntry.updateNodeAttr();
            highlightedNodeFringeEntry.swim();

        }
    }










    public void highlightNodes() {

        for (DijkstraNode highlightedNode : currentNode.connections.keySet()) {
            Integer currentNodeIsSource = currentNode.connections.get(highlightedNode).get(1);

            if (!highlightedNode.isMarked && currentNodeIsSource == 1) {
                int highlightedEdgeWeight = currentNode.connections.get(highlightedNode).get(0);
                int calculatedDistTo = currentNode.incumbentDistTo + highlightedEdgeWeight;

                if (calculatedDistTo < highlightedNode.incumbentDistTo) {
                    highlightedNode.updateHighlightedNode(calculatedDistTo, highlightedEdgeWeight);


                }
            }
        }

        visitNextNode();

    }












    public void visitNextNode(){

        DijkstraNode nextNode = this.dijkstraNodeList.get(this.fringe.getSmallestNodeNumber());

        fringe.removeSmallest();

        nextNode.markNode();

        this.currentNode = nextNode;


    }






    public void initDijkstraNodes(){

        for (GraphNode gNode : this.dijkstraGraph.graphNodeList) {

            DijkstraNode dNode = new DijkstraNode();

            dNode.number = gNode.number;
            dNode.incumbentDistTo = 999999999;

            if (dNode.number == originNodeNum){
                dNode.isMarked = true;
                dNode.incumbentDistTo = 0;
                dNode.edgeTo = null;
            }

            this.dijkstraNodeList.add(dNode);
        }

        this.originNode = dijkstraNodeList.get(originNodeNum);
        this.currentNode = dijkstraNodeList.get(originNodeNum);

        this.pathToArr = new int[dijkstraNodeList.size()];
    }



    public void initDijkstraNodeConnections(){


        for (GraphNode gNode : this.dijkstraGraph.graphNodeList){
            DijkstraNode dNode = dijkstraNodeList.get(gNode.number);
            dNode.connections = new LinkedHashMap<>();

            for (GraphNode neighborGNode : gNode.connections.keySet()) {
                DijkstraNode neighborDNode = dijkstraNodeList.get(neighborGNode.number);
                dNode.connections.put(neighborDNode, gNode.connections.get(neighborGNode));

            }
        }
    }


    public void initFringe(){

        this.fringe = new Fringe();

        for (DijkstraNode dNode : dijkstraNodeList){

            if (dNode.number != originNode.number){
                this.fringe.addNode(dNode);
            }
        }
    }



    public int calculateTotalDist(int nodeNum, int cumDist){

        DijkstraNode node = this.dijkstraNodeList.get(nodeNum);

        if (node.edgeTo == null){
            return cumDist;
        }

        else {
            int pathTo = this.pathToArr[nodeNum];
            return calculateTotalDist(node.edgeTo, cumDist) + pathTo;

        }
    }


    public void printPath(int nodeNum, int initCall){

        DijkstraNode node = dijkstraNodeList.get(nodeNum);

        if (node.edgeTo == null){
            System.out.print(originNode.number + " --> ");

        } else {
            printPath(node.edgeTo, 0);
            System.out.print(nodeNum);

            if (initCall != 1){
                System.out.print(" --> ");
            }
        }
    }










    public void targetedDijkstra(DirectedGraph graph, int originNodeNum, int targetNodeNum){

        if (targetNodeNum > graph.nodeCount-1){
            throw new java.lang.IndexOutOfBoundsException("TARGET NODE NOT CONTAINED WITHIN GRAPH");
        }

        initAlgorithm(graph, originNodeNum);

        initDijkstraNodes();
        initDijkstraNodeConnections();

        initFringe();



        while (this.currentNode.number != targetNodeNum){
            highlightNodes();
        }

        printPath(targetNodeNum, 1);
        System.out.println();
        System.out.println("Total Distance: " + calculateTotalDist(targetNodeNum,  0));

    }












}


