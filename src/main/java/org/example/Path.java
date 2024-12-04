package org.example;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import java.util.*;

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

    // Main GraphSearch method using BFSTemplate or DFSTemplate
    public static Path GraphSearch(Graph<String, DefaultEdge> graph, String src, String dst, Algorithm algo) {
        if (graph == null || src == null || dst == null) return null;

        if (!graph.containsVertex(src) || !graph.containsVertex(dst)) {
            System.out.println("Source or destination node not found in graph");
            return null;
        }

        if (src.equals(dst)) {
            Path path = new Path();
            path.addNode(src);
            return path;
        }

        if (algo == Algorithm.BFS) {
            return new BFSTemplate(graph).search(src, dst);
        } else {
            return new DFSTemplate(graph).search(src, dst);
        }
    }

    public List<String> getNodes() {
        return nodes;
    }
}





