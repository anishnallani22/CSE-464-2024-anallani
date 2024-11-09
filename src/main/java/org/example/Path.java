package org.example;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;

import java.util.*;

public class Path {
    private List<String> path;

    public Path(List<String> path) {
        this.path = path;
    }

    @Override
    public String toString() {
        if (path == null || path.isEmpty()) {
            return "No path found";
        }
        return String.join(" -> ", path);
    }

    public static Path graphSearchDFS(Graph<String, DefaultEdge> graph, String src, String dst) {
        Set<String> visited = new HashSet<>();
        List<String> path = new ArrayList<>();

        // Perform DFS
        boolean found = dfsHelper(graph, src, dst, visited, path);

        return found ? new Path(path) : null;
    }

    private static boolean dfsHelper(Graph<String, DefaultEdge> graph, String current, String dst,
                                     Set<String> visited, List<String> path) {
        visited.add(current);
        path.add(current);

        if (current.equals(dst)) {
            return true;
        }

        for (DefaultEdge edge : graph.outgoingEdgesOf(current)) {
            String neighbor = graph.getEdgeTarget(edge);
            if (!visited.contains(neighbor)) {
                boolean found = dfsHelper(graph, neighbor, dst, visited, path);
                if (found) {
                    return true;
                }
            }
        }


        path.remove(path.size() - 1);
        return false;
    }
}

