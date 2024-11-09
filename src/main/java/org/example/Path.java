package org.example;

import org.jgrapht.graph.DefaultEdge;

import java.util.*;

public class Path {
    private List<String> nodes;

    public Path(List<String> nodes) {
        this.nodes = nodes;
    }

    @Override
    public String toString() {
        return String.join(" -> ", nodes);
    }

    public List<String> getNodes() {
        return nodes;
    }

    // BFS Algorithm to find a path
    public static Path GraphSearchBFS(org.jgrapht.Graph<String, DefaultEdge> graph, String src, String dst) {
        if (!graph.containsVertex(src) || !graph.containsVertex(dst)) {
            return null;
        }

        Queue<String> queue = new LinkedList<>();
        Map<String, String> parentMap = new HashMap<>();
        queue.add(src);
        parentMap.put(src, null);

        while (!queue.isEmpty()) {
            String currentNode = queue.poll();

            if (currentNode.equals(dst)) {
                return buildPath(src, dst, parentMap);
            }

            for (DefaultEdge edge : graph.outgoingEdgesOf(currentNode)) {
                String neighbor = graph.getEdgeTarget(edge);
                if (!parentMap.containsKey(neighbor)) {
                    parentMap.put(neighbor, currentNode);
                    queue.add(neighbor);
                }
            }
        }
        return null;
    }

    private static Path buildPath(String src, String dst, Map<String, String> parentMap) {
        List<String> pathList = new ArrayList<>();
        for (String at = dst; at != null; at = parentMap.get(at)) {
            pathList.add(at);
        }
        Collections.reverse(pathList);
        return new Path(pathList);
    }
}

