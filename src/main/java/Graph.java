import java.util.*;

public class Graph {
    private final Map<String, List<Edge>> adjacencyList;
    private final List<Edge> edges;
    private final Set<String> vertices;
    
    public Graph() {
        this.adjacencyList = new HashMap<>();
        this.edges = new ArrayList<>();
        this.vertices = new HashSet<>();
    }
    
    public void addVertex(String vertex) {
        if (!adjacencyList.containsKey(vertex)) {
            adjacencyList.put(vertex, new ArrayList<>());
            vertices.add(vertex);
        }
    }
    
    public void addEdge(String from, String to, int weight) {
        addVertex(from);
        addVertex(to);
        
        Edge edge = new Edge(from, to, weight);
        adjacencyList.get(from).add(edge);
        adjacencyList.get(to).add(edge);
        edges.add(edge);
    }
    
    public Set<String> getVertices() {
        return new HashSet<>(vertices);
    }
    
    public List<Edge> getEdges() {
        return new ArrayList<>(edges);
    }
    
    public List<Edge> getAdjacentEdges(String vertex) {
        return adjacencyList.getOrDefault(vertex, new ArrayList<>());
    }
    
    public int getVertexCount() {
        return vertices.size();
    }
    
    public int getEdgeCount() {
        return edges.size();
    }
}