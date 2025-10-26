import java.util.*;

public class KruskalMST {
    private final Graph graph;
    private int operationsCount;
    
    public KruskalMST(Graph graph) {
        this.graph = graph;
        this.operationsCount = 0;
    }
    
    public MSTResult findMST() {
        long startTime = System.nanoTime();
        operationsCount = 0;
        
        List<Edge> edges = new ArrayList<>(graph.getEdges());
        Collections.sort(edges);
        operationsCount += edges.size() * (int) Math.log(edges.size());
        
        Map<String, String> parent = new HashMap<>();
        Map<String, Integer> rank = new HashMap<>();
        
        for (String vertex : graph.getVertices()) {
            parent.put(vertex, vertex);
            rank.put(vertex, 0);
            operationsCount += 2;
        }
        
        List<Edge> mstEdges = new ArrayList<>();
        int totalCost = 0;
        int edgesAdded = 0;
        
        for (Edge edge : edges) {
            operationsCount++;
            if (edgesAdded == graph.getVertexCount() - 1) break;
            
            String root1 = find(edge.getFrom(), parent);
            String root2 = find(edge.getTo(), parent);
            operationsCount += 2;
            
            if (!root1.equals(root2)) {
                mstEdges.add(edge);
                totalCost += edge.getWeight();
                union(root1, root2, parent, rank);
                edgesAdded++;
                operationsCount += 3;
            }
        }
        
        long endTime = System.nanoTime();
        double executionTimeMs = (endTime - startTime) / 1_000_000.0;
        
        return new MSTResult(mstEdges, totalCost, operationsCount, executionTimeMs);
    }
    
    private String find(String vertex, Map<String, String> parent) {
        operationsCount++;
        if (!parent.get(vertex).equals(vertex)) {
            parent.put(vertex, find(parent.get(vertex), parent));
            operationsCount++;
        }
        return parent.get(vertex);
    }
    
    private void union(String root1, String root2, Map<String, String> parent, Map<String, Integer> rank) {
        operationsCount++;
        if (rank.get(root1) < rank.get(root2)) {
            parent.put(root1, root2);
        } else if (rank.get(root1) > rank.get(root2)) {
            parent.put(root2, root1);
        } else {
            parent.put(root2, root1);
            rank.put(root1, rank.get(root1) + 1);
            operationsCount++;
        }
        operationsCount += 2;
    }
    
    public static class MSTResult {
        private final List<Edge> mstEdges;
        private final int totalCost;
        private final int operationsCount;
        private final double executionTimeMs;
        
        public MSTResult(List<Edge> mstEdges, int totalCost, int operationsCount, double executionTimeMs) {
            this.mstEdges = mstEdges;
            this.totalCost = totalCost;
            this.operationsCount = operationsCount;
            this.executionTimeMs = executionTimeMs;
        }
        
        public List<Edge> getMstEdges() {
            return mstEdges;
        }
        
        public int getTotalCost() {
            return totalCost;
        }
        
        public int getOperationsCount() {
            return operationsCount;
        }
        
        public double getExecutionTimeMs() {
            return executionTimeMs;
        }
    }
}