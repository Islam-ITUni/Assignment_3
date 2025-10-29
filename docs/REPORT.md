# MST Algorithms Analysis Report

## 1. Summary of Input Data and Algorithm Results

### Performance Results

| Dataset | Graph ID | Algorithm | Vertices | Edges | MST Cost | Time (ms) | Operations |
|---------|----------|-----------|----------|-------|----------|-----------|------------|
| Small 1 | 1 | Prim | 4 | 5 | 9 | 0.5876 | 23 |
| Small 1 | 1 | Kruskal | 4 | 5 | 9 | 0.2459 | 48 |
| Small 2 | 1 | Prim | 5 | 6 | 8 | 0.0313 | 29 |
| Small 2 | 1 | Kruskal | 5 | 6 | 8 | 0.0404 | 66 |
| Medium | 2 | Prim | 10 | 12 | 49 | 0.0637 | 64 |
| Medium | 2 | Kruskal | 10 | 12 | 49 | 0.0627 | 181 |
| Large | 3 | Prim | 20 | 27 | 190 | 0.1506 | 136 |
| Large | 3 | Kruskal | 20 | 27 | 190 | 0.1224 | 371 |

## 2. Algorithm Comparison: Theory vs Practice

### Theoretical Analysis
- **Prim's Algorithm**: O(E log V) with priority queue
- **Kruskal's Algorithm**: O(E log E) due to edge sorting

### Practical Performance Analysis

#### Execution Time Comparison:
- **Small Graph 1**: Kruskal faster (0.2459 ms vs 0.5876 ms)
- **Small Graph 2**: Prim faster (0.0313 ms vs 0.0404 ms)  
- **Medium Graph**: Nearly identical (0.0637 ms vs 0.0627 ms)
- **Large Graph**: Kruskal faster (0.1224 ms vs 0.1506 ms)

#### Operation Count Analysis:
- **Prim**: 23-136 operations (more efficient)
- **Kruskal**: 48-371 operations (2.1-2.8× more due to sorting)
- **Prim is consistently more operation-efficient**

## 3. Conclusions

### Prim's Algorithm is preferable for:
- Dense graphs (many edges)
- Memory-constrained environments
- Applications where operation count matters
- Real-time systems with limited CPU cycles

### Kruskal's Algorithm is preferable for:
- Sparse graphs (few edges)
- When code simplicity is important
- Distributed computing scenarios
- When edges can be pre-sorted

### Key Findings:
1. Both algorithms produced identical MST costs (correctness verified)
2. All MSTs contain exactly V-1 edges (acyclic property confirmed)
3. Prim is more operation-efficient (2-3× fewer operations)
4. Execution times are very close for small-to-medium graphs

## 4. Automated Testing Results
-  6/6 JUnit tests passed
-  All correctness requirements verified
-  Performance metrics validated
-  Results reproducible across runs

## 5. References
- Introduction to Algorithms (Cormen et al.)
- Java Documentation
- JUnit 5 Framework
