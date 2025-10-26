import java.util.*;

public class PrimMST {
    private final Graph graph;
    
    public PrimMST(Graph graph) {
        this.graph = graph;
    }
    
    public MSTResult findMST() {
        long startTime = System.nanoTime();
        int operationsCount = 0;
        
        if (graph.getVertexCount() == 0) {
            return new MSTResult(new ArrayList<>(), 0, operationsCount, 0);
        }
        
        Set<String> visited = new HashSet<>();
        List<Edge> mstEdges = new ArrayList<>();
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        int totalCost = 0;
        
        String startVertex = graph.getVertices().iterator().next();
        visited.add(startVertex);
        operationsCount++;
        
        pq.addAll(graph.getAdjacentEdges(startVertex));
        operationsCount += graph.getAdjacentEdges(startVertex).size();
        
        while (!pq.isEmpty() && visited.size() < graph.getVertexCount()) {
            Edge edge = pq.poll();
            operationsCount++;
            
            String nextVertex = null;
            if (visited.contains(edge.getFrom()) && !visited.contains(edge.getTo())) {
                nextVertex = edge.getTo();
            } else if (visited.contains(edge.getTo()) && !visited.contains(edge.getFrom())) {
                nextVertex = edge.getFrom();
            }
            
            if (nextVertex != null) {
                visited.add(nextVertex);
                mstEdges.add(edge);
                totalCost += edge.getWeight();
                operationsCount += 2;
                
                for (Edge adjEdge : graph.getAdjacentEdges(nextVertex)) {
                    operationsCount++;
                    if (!visited.contains(adjEdge.getFrom()) || !visited.contains(adjEdge.getTo())) {
                        pq.add(adjEdge);
                        operationsCount++;
                    }
                }
            }
        }
        
        long endTime = System.nanoTime();
        double executionTimeMs = (endTime - startTime) / 1_000_000.0;
        
        return new MSTResult(mstEdges, totalCost, operationsCount, executionTimeMs);
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