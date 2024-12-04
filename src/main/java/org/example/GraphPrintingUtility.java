package org.example;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;

public class GraphPrintingUtility {
    public static String printGraph(Graph<String, DefaultEdge> graph) {
        StringBuilder sb = new StringBuilder();
        sb.append("Graph Details:\n");
        sb.append("Number of nodes: ").append(graph.vertexSet().size()).append("\n");
        sb.append("Nodes: ").append(graph.vertexSet()).append("\n");
        sb.append("Number of edges: ").append(graph.edgeSet().size()).append("\n");
        sb.append("Edges:\n");
        graph.edgeSet().forEach(edge -> {
            String source = graph.getEdgeSource(edge);
            String target = graph.getEdgeTarget(edge);
            sb.append(source).append(" -> ").append(target).append("\n");
        });
        return sb.toString();
    }
}

