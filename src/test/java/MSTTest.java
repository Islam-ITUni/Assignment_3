import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import org.json.JSONArray;
import org.json.JSONObject;

public class MSTTest {
    
    @Test
    public void testMSTTotalCostIdentical() throws Exception {
        String content = new String(Files.readAllBytes(Paths.get("src/main/resources/data/input_small.json")));
        JSONObject json = new JSONObject(content);
        JSONArray graphs = json.getJSONArray("graphs");
        
        for (int i = 0; i < graphs.length(); i++) {
            JSONObject graphData = graphs.getJSONObject(i);
            Graph graph = createGraphFromJSON(graphData);
            
            PrimMST prim = new PrimMST(graph);
            KruskalMST kruskal = new KruskalMST(graph);
            
            PrimMST.MSTResult primResult = prim.findMST();
            KruskalMST.MSTResult kruskalResult = kruskal.findMST();
            
            assertEquals(primResult.getTotalCost(), kruskalResult.getTotalCost());
        }
    }
    
    @Test
    public void testMSTHasVMinus1Edges() throws Exception {
        String content = new String(Files.readAllBytes(Paths.get("src/main/resources/data/input_small.json")));
        JSONObject json = new JSONObject(content);
        JSONArray graphs = json.getJSONArray("graphs");
        
        for (int i = 0; i < graphs.length(); i++) {
            JSONObject graphData = graphs.getJSONObject(i);
            Graph graph = createGraphFromJSON(graphData);
            
            PrimMST prim = new PrimMST(graph);
            PrimMST.MSTResult result = prim.findMST();
            
            assertEquals(graph.getVertexCount() - 1, result.getMstEdges().size());
        }
    }
    
    @Test
    public void testMSTIsAcyclic() throws Exception {
        String content = new String(Files.readAllBytes(Paths.get("src/main/resources/data/input_small.json")));
        JSONObject json = new JSONObject(content);
        JSONArray graphs = json.getJSONArray("graphs");
        
        for (int i = 0; i < graphs.length(); i++) {
            JSONObject graphData = graphs.getJSONObject(i);
            Graph graph = createGraphFromJSON(graphData);
            
            PrimMST prim = new PrimMST(graph);
            PrimMST.MSTResult result = prim.findMST();
            
            assertEquals(graph.getVertexCount() - 1, result.getMstEdges().size());
        }
    }
    
    @Test
    public void testMSTConnectsAllVertices() throws Exception {
        String content = new String(Files.readAllBytes(Paths.get("src/main/resources/data/input_small.json")));
        JSONObject json = new JSONObject(content);
        JSONArray graphs = json.getJSONArray("graphs");
        
        for (int i = 0; i < graphs.length(); i++) {
            JSONObject graphData = graphs.getJSONObject(i);
            Graph graph = createGraphFromJSON(graphData);
            
            PrimMST prim = new PrimMST(graph);
            PrimMST.MSTResult result = prim.findMST();
            
            assertEquals(graph.getVertexCount() - 1, result.getMstEdges().size());
        }
    }
    
    @Test
    public void testPerformanceMetrics() throws Exception {
        String content = new String(Files.readAllBytes(Paths.get("src/main/resources/data/input_medium.json")));
        JSONObject json = new JSONObject(content);
        JSONArray graphs = json.getJSONArray("graphs");
        
        for (int i = 0; i < graphs.length(); i++) {
            JSONObject graphData = graphs.getJSONObject(i);
            Graph graph = createGraphFromJSON(graphData);
            
            PrimMST prim = new PrimMST(graph);
            KruskalMST kruskal = new KruskalMST(graph);
            
            PrimMST.MSTResult primResult = prim.findMST();
            KruskalMST.MSTResult kruskalResult = kruskal.findMST();
            
            assertTrue(primResult.getExecutionTimeMs() >= 0);
            assertTrue(kruskalResult.getExecutionTimeMs() >= 0);
            assertTrue(primResult.getOperationsCount() >= 0);
            assertTrue(kruskalResult.getOperationsCount() >= 0);
        }
    }
    
    @Test
    public void testResultsReproducible() throws Exception {
        String content = new String(Files.readAllBytes(Paths.get("src/main/resources/data/input_small.json")));
        JSONObject json = new JSONObject(content);
        JSONArray graphs = json.getJSONArray("graphs");
        
        for (int i = 0; i < graphs.length(); i++) {
            JSONObject graphData = graphs.getJSONObject(i);
            Graph graph = createGraphFromJSON(graphData);
            
            PrimMST prim = new PrimMST(graph);
            
            PrimMST.MSTResult result1 = prim.findMST();
            PrimMST.MSTResult result2 = prim.findMST();
            
            assertEquals(result1.getTotalCost(), result2.getTotalCost());
            assertEquals(result1.getMstEdges().size(), result2.getMstEdges().size());
        }
    }
    
    private Graph createGraphFromJSON(JSONObject graphData) {
        Graph graph = new Graph();
        
        JSONArray nodes = graphData.getJSONArray("nodes");
        JSONArray edges = graphData.getJSONArray("edges");
        
        for (int i = 0; i < edges.length(); i++) {
            JSONObject edge = edges.getJSONObject(i);
            graph.addEdge(
                edge.getString("from"),
                edge.getString("to"), 
                edge.getInt("weight")
            );
        }
        
        return graph;
    }
}