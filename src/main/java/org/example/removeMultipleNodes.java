package org.example;

import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.Graph;
import java.util.NoSuchElementException;
import java.util.Set;

public class removeMultipleNodes {
    private Graph<String, DefaultEdge> graph;

    // Constructor to initialize the graph
    public removeMultipleNodes(Graph<String, DefaultEdge> graph) {
        this.graph = graph;
    }

    // Method to remove multiple nodes
    public void removeNodes(String[] labels) {
        for (String label : labels) {
            // Check if the node exists before attempting to remove it
            if (!graph.containsVertex(label)) {
                throw new NoSuchElementException("Node with label '" + label + "' does not exist.");
            }

            // Remove all edges connected to the node
            Set<DefaultEdge> edgesToRemove = graph.edgesOf(label);
            for (DefaultEdge edge : edgesToRemove) {
                graph.removeEdge(edge);
            }

            // Remove the node itself
            graph.removeVertex(label);
            System.out.println("Node '" + label + "' removed successfully.");
        }
    }

    //Print Graph Details
//    public String printGraph() {
//        StringBuilder sb = new StringBuilder();
//        sb.append("Graph Details after removing nodes:\n");
//        sb.append("Number of nodes: ").append(graph.vertexSet().size()).append("\n");
//        sb.append("Nodes: ").append(graph.vertexSet()).append("\n");
//        sb.append("Number of edges: ").append(graph.edgeSet().size()).append("\n");
//        sb.append("Edges:\n");
//        graph.edgeSet().forEach(edge -> {
//            String source = graph.getEdgeSource(edge);
//            String target = graph.getEdgeTarget(edge);
//            sb.append(source).append(" -> ").append(target).append("\n");
//        });
//        return sb.toString();
//    }
}

