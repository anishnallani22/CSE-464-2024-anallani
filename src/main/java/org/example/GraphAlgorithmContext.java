package org.example;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;

public class GraphAlgorithmContext {
    private GraphStrategyPattern strategy;

    public GraphAlgorithmContext(GraphStrategyPattern strategy) {
        this.strategy = strategy;
    }

    public Path performSearch(Graph<String, DefaultEdge> graph, String src, String dst) {
        return strategy.search(graph, src, dst);
    }
}

