package org.example;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;

import java.util.*;
//Have template class to implement strategy (extend template class)
public class BFSTemplate implements GraphStrategyPattern {
    private final Graph<String, DefaultEdge> graph;

    public BFSTemplate(Graph<String, DefaultEdge> graph) {
        this.graph = graph;
    }

    @Override
    public Graph<String, DefaultEdge> getGraph() {
        return graph;
    }

    @Override
    public Path search(Graph<String, DefaultEdge> graph, String src, String dst) {
        if (!graph.containsVertex(src) || !graph.containsVertex(dst)) {
            System.out.println("Source or destination node not found");
            return null;
        }

        // BFS logic
        Queue<String> queue = new LinkedList<>();
        Map<String, String> parentMap = new HashMap<>();
        Set<String> visited = new HashSet<>();

        queue.add(src);
        visited.add(src);

        while (!queue.isEmpty()) {
            String current = queue.poll();

            if (current.equals(dst)) {
                return buildPath(parentMap, src, dst);
            }

            for (DefaultEdge edge : graph.outgoingEdgesOf(current)) {
                String neighbor = graph.getEdgeTarget(edge).equals(current) ? graph.getEdgeSource(edge) : graph.getEdgeTarget(edge);

                if (!visited.contains(neighbor)) {
                    queue.add(neighbor);
                    visited.add(neighbor);
                    parentMap.put(neighbor, current);
                }
            }
        }
        return null; // No path found
    }

    private Path buildPath(Map<String, String> parentMap, String src, String dst) {
        Path path = new Path();
        for (String node = dst; node != null; node = parentMap.get(node)) {
            path.addNode(node);
        }
        Collections.reverse(path.getNodes());
        return path;
    }
}








