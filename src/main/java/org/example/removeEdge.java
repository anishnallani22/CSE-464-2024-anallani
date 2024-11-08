package org.example;

import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.Graph;

import java.util.NoSuchElementException;

public class removeEdge {
    private Graph<String, DefaultEdge> graph;


    public removeEdge(Graph<String, DefaultEdge> graph) {
        this.graph = graph;
    }

    public void removeEdge(String srcLabel, String dstLabel) {
        //Verify if node exists
        if (!graph.containsVertex(srcLabel) || !graph.containsVertex(dstLabel)) {
            throw new NoSuchElementException("One or both nodes do not exist.");
        }

        //Verify if edge exists
        if (!graph.containsEdge(srcLabel, dstLabel)) {
            throw new NoSuchElementException("Edge between '" + srcLabel + "' and '" + dstLabel + "' does not exist.");
        }

        // Remove the edge
        graph.removeEdge(srcLabel, dstLabel);
        System.out.println("Edge from '" + srcLabel + "' to '" + dstLabel + "' removed successfully.");
    }

    // Print graph details
    public String printGraph() {
        StringBuilder sb = new StringBuilder();
        sb.append("Graph Details after removing edge:\n");
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




