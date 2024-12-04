package org.example;

import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.Graph;

public class addEdges {
    private Graph<String, DefaultEdge> graph;

    public addEdges(Graph<String, DefaultEdge> graph) {
        this.graph = graph;
    }

    private boolean checkNodeExists(String label) {
        if (!graph.containsVertex(label)) {
            System.out.println("Node '" + label + "' does not exist.");
            return false;
        }
        return true;
    }

    public void addEdge(String srcLabel, String dstLabel) {
        if (!checkNodeExists(srcLabel) || !checkNodeExists(dstLabel)) {
            return;
        }

        if (graph.containsEdge(srcLabel, dstLabel)) {
            System.out.println("Edge from '" + srcLabel + "' to '" + dstLabel + "' already exists.");
        } else {
            graph.addEdge(srcLabel, dstLabel);
            System.out.println("Edge added from '" + srcLabel + "' to '" + dstLabel + "'.");
        }
    }

//    public String printGraph() {
//        StringBuilder sb = new StringBuilder();
//        sb.append("Graph Details after adding edges:\n");
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
