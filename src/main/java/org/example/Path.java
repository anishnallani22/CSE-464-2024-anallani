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

    // Main GraphSearch method using BFS or DFS based on the algorithm
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
            return graphSearchBFS(graph, src, dst);
        } else {
            return graphSearchDFS(graph, src, dst);
        }
    }

    // BFS algorithm
    private static Path graphSearchBFS(Graph<String, DefaultEdge> graph, String src, String dst) {
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
        return null;
    }

    // DFS algorithm
    private static Path graphSearchDFS(Graph<String, DefaultEdge> graph, String src, String dst) {
        Stack<String> stack = new Stack<>();
        Map<String, String> parentMap = new HashMap<>();
        Set<String> visited = new HashSet<>();

        stack.push(src);
        visited.add(src);

        while (!stack.isEmpty()) {
            String current = stack.pop();

            if (current.equals(dst)) {
                return buildPath(parentMap, src, dst);
            }

            for (DefaultEdge edge : graph.outgoingEdgesOf(current)) {
                String neighbor = graph.getEdgeTarget(edge);
                if (neighbor.equals(current)) {
                    neighbor = graph.getEdgeSource(edge);
                }
                if (!visited.contains(neighbor)) {
                    stack.push(neighbor);
                    visited.add(neighbor);
                    parentMap.put(neighbor, current);
                }
            }
        }
        return null;
    }

    // Build the path from the parent map
    private static Path buildPath(Map<String, String> parentMap, String src, String dst) {
        Path path = new Path();
        for (String node = dst; node != null; node = parentMap.get(node)) {
            path.addNode(node);
        }
        Collections.reverse(path.nodes);
        return path;
    }
}



