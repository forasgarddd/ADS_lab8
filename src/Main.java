public class Main {

    public static void main(String[] args) {
        Graph graph = new Graph();
        Node a = new Node(0, "0");
        Node b = new Node(1, "1");
        Node c = new Node(2, "2");
        Node d = new Node(3, "3");
        Node e = new Node(4, "4");
        Node f = new Node(5, "5");
        Node g = new Node(6, "6");
        Node h = new Node(7, "7");
        Node i = new Node(8, "8");
        Node j = new Node(9, "9");
        Node k = new Node(10, "10");
        Node l = new Node(11, "11");
        Node m = new Node(12, "12");
        Node n = new Node(13, "13");
        Node o = new Node(14, "14");
        Node p = new Node(15, "15");
        Node q = new Node(16, "16");
        Node r = new Node(17, "17");
        Node s = new Node(18, "18");
        Node t = new Node(19, "19");


        graph.addEdge(a,b);
        graph.addEdge(b,c);
        graph.addEdge(c,d);
        graph.addEdge(d,e);
        graph.addEdge(e,f);
        graph.addEdge(f,g);
        graph.addEdge(g,h);
        graph.addEdge(h,i);
        graph.addEdge(i,j);
        graph.addEdge(j,k);
        graph.addEdge(k,l);
        graph.addEdge(l,m);
        graph.addEdge(m,n);
        graph.addEdge(n,o);
        graph.addEdge(o,p);
        graph.addEdge(p,q);
        graph.addEdge(q,r);
        graph.addEdge(r,s);
        graph.addEdge(s,t);
        graph.addEdge(t,a);
        graph.addEdge(a,k);
        graph.addEdge(b,l);
        graph.addEdge(c,m);
        graph.addEdge(d,n);
        graph.addEdge(e,o);
        graph.addEdge(f,p);
        graph.addEdge(g,q);
        graph.addEdge(h,r);
        graph.addEdge(i,s);
        graph.addEdge(j,t);

        System.out.println("BFS:");
        graph.breadthFirstSearch(a);

        graph.resetNodesVisited();

        System.out.println("DFS:");
        graph.depthFirstSearch(b);
        System.out.println("\n");

        graph.resetNodesVisited();
        graph.DijkstraShortestPath(a, s);
    }
}
