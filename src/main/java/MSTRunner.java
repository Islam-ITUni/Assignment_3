import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import org.json.JSONArray;
import org.json.JSONObject;

public class MSTRunner {
    public static void main(String[] args) {
        System.out.println("=== MSTRunner Started ===");
        
        try {
            String currentDir = System.getProperty("user.dir");
            String basePath = currentDir + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "data" + File.separator;
            
            JSONArray allResults = new JSONArray();
            
            processFile(basePath + "input_small.json", allResults);
            processFile(basePath + "input_medium.json", allResults);
            processFile(basePath + "input_large.json", allResults);
            
            String outputPath = basePath + "output_results.json";
            JSONObject output = new JSONObject();
            output.put("results", allResults);
            
            Files.write(Paths.get(outputPath), output.toString(4).getBytes());
            System.out.println("All files processed! Total graphs: " + allResults.length());
            System.out.println("Output written to: " + outputPath);
            
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    private static void processFile(String filename, JSONArray allResults) {
        try {
            System.out.println("Processing: " + filename);
            
            File inputFile = new File(filename);
            if (!inputFile.exists()) {
                System.out.println("File not found: " + filename);
                return;
            }
            
            String content = new String(Files.readAllBytes(Paths.get(filename)));
            JSONObject json = new JSONObject(content);
            JSONArray graphs = json.getJSONArray("graphs");
            
            for (int i = 0; i < graphs.length(); i++) {
                JSONObject graphData = graphs.getJSONObject(i);
                Graph graph = createGraphFromJSON(graphData);
                
                PrimMST prim = new PrimMST(graph);
                KruskalMST kruskal = new KruskalMST(graph);
                
                PrimMST.MSTResult primResult = prim.findMST();
                KruskalMST.MSTResult kruskalResult = kruskal.findMST();
                
                JSONObject result = new JSONObject();
                result.put("graph_id", graphData.getInt("id"));
                
                JSONObject inputStats = new JSONObject();
                inputStats.put("vertices", graph.getVertexCount());
                inputStats.put("edges", graph.getEdgeCount());
                result.put("input_stats", inputStats);
                
                JSONObject primJson = new JSONObject();
                primJson.put("total_cost", primResult.getTotalCost());
                primJson.put("operations_count", primResult.getOperationsCount());
                primJson.put("execution_time_ms", primResult.getExecutionTimeMs());
                primJson.put("mst_edges", convertEdgesToJson(primResult.getMstEdges()));
                result.put("prim", primJson);
                
                JSONObject kruskalJson = new JSONObject();
                kruskalJson.put("total_cost", kruskalResult.getTotalCost());
                kruskalJson.put("operations_count", kruskalResult.getOperationsCount());
                kruskalJson.put("execution_time_ms", kruskalResult.getExecutionTimeMs());
                kruskalJson.put("mst_edges", convertEdgesToJson(kruskalResult.getMstEdges()));
                result.put("kruskal", kruskalJson);
                
                allResults.put(result);
            }
            
            System.out.println("Successfully processed: " + filename);
            
        } catch (Exception e) {
            System.out.println("Error processing " + filename + ": " + e.getMessage());
        }
    }
    
    private static Graph createGraphFromJSON(JSONObject graphData) {
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
    
    private static JSONArray convertEdgesToJson(List<Edge> edges) {
        JSONArray array = new JSONArray();
        for (Edge edge : edges) {
            JSONObject edgeJson = new JSONObject();
            edgeJson.put("from", edge.getFrom());
            edgeJson.put("to", edge.getTo());
            edgeJson.put("weight", edge.getWeight());
            array.put(edgeJson);
        }
        return array;
    }
}