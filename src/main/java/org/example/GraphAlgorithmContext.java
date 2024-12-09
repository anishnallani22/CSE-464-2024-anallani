package org.example;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;

public class GraphAlgorithmContext {
    /**
     * Perform a graph search based on the specified algorithm.
     *
     * @param graph The graph to search.
     * @param src   The source node.
     * @param dst   The destination node.
     * @param algo  The algorithm to use (BFS or DFS).
     * @return The path found or null if no path exists.
     */
    public static Path GraphSearch(Graph<String, DefaultEdge> graph, String src, String dst, Algorithm algo) {
        // Validate inputs
        if (graph == null || src == null || dst == null || algo == null) {
            throw new IllegalArgumentException("Invalid input parameters");
        }

        // Select strategy based on the algorithm enum
        GraphStrategyPattern strategy;
        switch (algo) {
            case BFS:
                strategy = new BFSTemplate(graph);
                break;
            case DFS:
                strategy = new DFSTemplate(graph);
                break;
            default:
                throw new IllegalArgumentException("Unknown algorithm: " + algo);
        }

        // Use the selected strategy to perform the search
        return strategy.search(graph, src, dst);
    }
}


