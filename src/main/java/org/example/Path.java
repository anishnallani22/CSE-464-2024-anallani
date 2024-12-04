package org.example;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;

import java.util.ArrayList;
import java.util.List;

public class Path {
    private List<String> nodes;

    public Path() {
        this.nodes = new ArrayList<>();
    }

    public void addNode(String node) {
        nodes.add(node);
    }

    @Override
    public String toString() {
        return String.join(" -> ", nodes);
    }

    //Strategy Pattern
    public static Path GraphSearch(Graph<String, DefaultEdge> graph, String src, String dst, Algorithm algo) {
        if (graph == null || src == null || dst == null) {
            System.out.println("Invalid input: graph, source, or destination is null.");
            return null;
        }

        // Check if source and destination nodes exist in the graph
        if (!graph.containsVertex(src) || !graph.containsVertex(dst)) {
            System.out.println("Source or destination node not found in graph");
            return null;
        }


        if (src.equals(dst)) {
            Path path = new Path();
            path.addNode(src);
            return path;
        }

        GraphStrategyPattern strategy;
        if (algo == Algorithm.BFS) {
            strategy = new BFSTemplate(graph);
        } else if (algo == Algorithm.DFS) {
            strategy = new DFSTemplate(graph);
        } else {
            throw new IllegalArgumentException("Unknown algorithm: " + algo);
        }

        GraphAlgorithmContext context = new GraphAlgorithmContext(strategy);
        return context.performSearch(graph, src, dst);
    }

    public List<String> getNodes() {
        return nodes;
    }
}



