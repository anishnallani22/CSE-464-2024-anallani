package org.example;

import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.Graph;

public class NodeModifier {
    private Graph<String, DefaultEdge> graph;

    // Constructor that takes in the existing graph from Feature 1
    public NodeModifier(Graph<String, DefaultEdge> graph) {
        this.graph = graph;
    }

    public void addNode(String label) {
        if (graph.containsVertex(label)) {
            System.out.println("Node with label '" + label + "' already exists.");
        } else {
            graph.addVertex(label);
            System.out.println("Node '" + label + "' added successfully.");
        }
    }

    public void addNodes(String[] labels) {
        for (String label : labels) {
            addNode(label);
        }
    }

//    public String printGraph() {
//        StringBuilder sb = new StringBuilder();
//        sb.append("Graph Details after adding nodes:\n");
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

