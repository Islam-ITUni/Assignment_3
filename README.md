Project Overview
This project implements and compares Prim's and Kruskal's algorithms for finding Minimum Spanning Trees (MST) in weighted undirected graphs. The implementation includes comprehensive testing, performance analysis, and automated verification of algorithm correctness.

Project Structure
text
Assignment_3/
├── src/main/java/          
│   ├── Graph.java         
│   ├── Edge.java          
│   ├── PrimMST.java       
│   ├── KruskalMST.java    
│   └── MSTRunner.java    
├── src/test/java/         
│   └── MSTTest.java       
├── src/main/resources/data/ 
│   ├── input_small.json   
│   ├── input_medium.json  
│   ├── input_large.json   
│   └── output_results.json 
├── docs/                  
│   ├── REPORT.md          
│   ├── performance_comparison.csv
│   └── performance_summary.csv
├── test_results.png       
├── README.md              
└── pom.xml               
Algorithms Implemented
Prim's Algorithm
Time Complexity: O(E log V) with binary heap

Space Complexity: O(V + E)

Best For: Dense graphs, memory-efficient applications

Kruskal's Algorithm
Time Complexity: O(E log E) due to sorting

Space Complexity: O(V + E)

Best For: Sparse graphs, distributed computing

Features
- Both MST algorithms implemented

- Custom graph data structure (bonus requirement)

- Comprehensive JUnit test suite (6 tests)

- Multiple input datasets (small, medium, large)

- Performance measurement (time and operations)

- JSON input/output support

- Automated correctness verification

Test Results
https://test_results.png

6/6 Tests Passed:

- testMSTTotalCostIdentical

- testMSTHasVMinus1Edges

- testMSTIsAcyclic

- testMSTConnectsAllVertices

- testPerformanceMetrics

- testResultsReproducible

Performance Summary
Dataset	Vertices	Edges	Prim Time	Kruskal Time	Prim Ops	Kruskal Ops
Small 1	4	5	0.5876 ms	0.2459 ms	23	48
Small 2	5	6	0.0313 ms	0.0404 ms	29	66
Medium	10	12	0.0637 ms	0.0627 ms	64	181
Large	20	27	0.1506 ms	0.1224 ms	136	371
How to Run
Prerequisites
Java 11+

Maven 3.6+

Running Tests
bash
mvn test
Generating Results
bash
mvn compile exec:java -Dexec.mainClass="MSTRunner"
In IntelliJ IDEA
Open project

Run MSTTest.java for automated testing

Run MSTRunner.java to generate output results

Key Findings
Correctness Verified: Both algorithms produce identical MST costs

Efficiency: Prim uses 2-3× fewer operations than Kruskal

Performance: Both algorithms execute in sub-millisecond times

Reliability: All automated tests pass consistently