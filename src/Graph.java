import java.util.*;


class Node {
    int n;
    String name;
    boolean visited;
    LinkedList<Edge> edges;

    Node(int n, String name) {
        this.n = n;
        this.name = name;
        visited = false;
        edges = new LinkedList<>();
    }

    void visit() {
        visited = true;
    }

    void unvisit() {
        visited = false;
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }
}

class Edge {
    Node source;
    Node destination;
    Edge(Node s, Node d) {
        source = s;
        destination = d;
    }
}

public class Graph {

    private Set<Node> nodes;
    private HashMap<Node, LinkedList<Node>> adjacencyMap;

    public Graph() {
        adjacencyMap = new HashMap<>();
        nodes = new HashSet<>();
    }


    public void addEdgeHelper(Node a, Node b) {
        LinkedList<Node> tmp = adjacencyMap.get(a);

        if (tmp != null) {
            tmp.remove(b);
        }
        else tmp = new LinkedList<>();
        tmp.add(b);
        adjacencyMap.put(a,tmp);
        a.edges.add(new Edge(a, b));
    }

    public void removeEdgeHelper(Node a, Node b) {
        LinkedList<Node> tmp = adjacencyMap.get(a);
        if (tmp != null) {
            tmp.remove(b);
        }
        else tmp = new LinkedList<>();
        tmp.remove(b);
        adjacencyMap.remove(a, tmp);
        a.edges.remove(new Edge(a, b));
    }

    public void addEdge(Node source, Node destination) {

        nodes.add(source);
        nodes.add(destination);

        if (!adjacencyMap.keySet().contains(source))
            adjacencyMap.put(source, null);

        if (!adjacencyMap.keySet().contains(destination))
            adjacencyMap.put(destination, null);

        addEdgeHelper(source, destination);

        addEdgeHelper(destination, source);
    }

    public void removeEdge(Node source, Node destination) {
        nodes.remove(source);
        nodes.remove(destination);

        if (!adjacencyMap.keySet().contains(source))
            adjacencyMap.put(source, null);

        if (!adjacencyMap.keySet().contains(destination))
            adjacencyMap.put(destination, null);

        removeEdgeHelper(source, destination);

        removeEdgeHelper(destination, source);
    }

    public void printEdges() {
        for (Node node : adjacencyMap.keySet()) {
            System.out.print("The " + node.name + " has an edge towards: ");
            for (Node neighbor : adjacencyMap.get(node)) {
                System.out.print(neighbor.name + " ");
            }
            System.out.println();
        }
    }

    public boolean hasEdge(Node source, Node destination) {
        return adjacencyMap.containsKey(source) && adjacencyMap.get(source).contains(destination);
    }

    public void resetNodesVisited(){
        for(Node node : adjacencyMap.keySet()){
            node.unvisit();
        }
    }
    void breadthFirstSearch(Node node) {


        if (node == null)
            return;


        LinkedList<Node> queue = new LinkedList<>();
        queue.add(node);

        while (!queue.isEmpty()) {
            Node currentFirst = queue.removeFirst();


            if (currentFirst.isVisited())
                continue;


            currentFirst.visit();
            System.out.print(currentFirst.name + " ");

            LinkedList<Node> allNeighbors = adjacencyMap.get(currentFirst);


            if (allNeighbors == null)
                continue;

            for (Node neighbor : allNeighbors) {
                if (!neighbor.isVisited()) {
                    queue.add(neighbor);
                }
            }
        }
        System.out.println();
    }
    public void depthFirstSearch(Node node) {
        node.visit();
        System.out.print(node.name + " ");

        LinkedList<Node> allNeighbors = adjacencyMap.get(node);
        if (allNeighbors == null)
            return;

        for (Node neighbor : allNeighbors) {
            if (!neighbor.isVisited())
                depthFirstSearch(neighbor);
        }
    }

    public void DijkstraShortestPath(Node start, Node end) {

        HashMap<Node, Node> changedAt = new HashMap<>();
        changedAt.put(start, null);

        HashMap<Node, Double> shortestPathMap = new HashMap<>();


        for (Node node : nodes) {
            if (node == start)
                shortestPathMap.put(start, 0.0);
            else shortestPathMap.put(node, Double.POSITIVE_INFINITY);
        }


        for (Edge edge : start.edges) {
            shortestPathMap.put(edge.destination, 1.0);
            changedAt.put(edge.destination, start);
        }

        start.visit();

        while (true) {
            Node currentNode = closestReachableUnvisited(shortestPathMap);

            if (currentNode == null) {
                System.out.println("There isn't a path between " + start.name + " and " + end.name);
                return;
            }

            if (currentNode == end) {
                System.out.println("The path with the smallest weight between "
                        + start.name + " and " + end.name + " is:");

                Node child = end;


                String path = end.name;
                while (true) {
                    Node parent = changedAt.get(child);
                    if (parent == null) {
                        break;
                    }


                    path = parent.name + " " + path;
                    child = parent;
                }
                System.out.println(path);
                System.out.println("The path costs: " + shortestPathMap.get(end));
                return;
            }
            currentNode.visit();

            for (Edge edge : currentNode.edges) {
                if (edge.destination.isVisited())
                    continue;

                if (shortestPathMap.get(currentNode)
                        + 1.0
                        < shortestPathMap.get(edge.destination)) {
                    shortestPathMap.put(edge.destination,
                            shortestPathMap.get(currentNode) + 1.0);
                    changedAt.put(edge.destination, currentNode);
                }
            }
        }
    }
    private Node closestReachableUnvisited(HashMap<Node, Double> shortestPathMap) {

        double shortestDistance = Double.POSITIVE_INFINITY;
        Node closestReachableNode = null;
        for (Node node : nodes) {
            if (node.isVisited())
                continue;

            double currentDistance = shortestPathMap.get(node);
            if (currentDistance == Double.POSITIVE_INFINITY)
                continue;

            if (currentDistance < shortestDistance) {
                shortestDistance = currentDistance;
                closestReachableNode = node;
            }
        }
        return closestReachableNode;
    }
}