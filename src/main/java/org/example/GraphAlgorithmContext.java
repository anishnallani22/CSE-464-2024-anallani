package org.example;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;

public class GraphAlgorithmContext {
    private GraphStrategyPattern strategy;

    public GraphAlgorithmContext(GraphStrategyPattern strategy) {
        this.strategy = strategy;
    }

    public static Path GraphSearch(Graph<String, DefaultEdge> graph, String src, String dst, GraphStrategyPattern strategy) {
        // Validate inputs
        if (graph == null || src == null || dst == null || strategy == null) {
            throw new IllegalArgumentException("Invalid input parameters");
        }

        // Execute the search strategy
        return strategy.search(graph, src, dst);
    }

}

