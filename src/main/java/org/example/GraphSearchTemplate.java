package org.example;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;

import java.util.*;

public abstract class GraphSearchTemplate {
    protected Graph<String, DefaultEdge> graph;
    protected Set<String> visited;
    protected Map<String, String> parentMap;

    public GraphSearchTemplate(Graph<String, DefaultEdge> graph) {
        this.graph = graph;
        this.visited = new HashSet<>();
        this.parentMap = new HashMap<>();
    }

    public Path search(String src, String dst) {
        if (!graph.containsVertex(src) || !graph.containsVertex(dst)) {
            System.out.println("Source or destination node not found in graph");
            return null;
        }

        if (src.equals(dst)) {
            Path path = new Path();
            path.addNode(src);
            return path;
        }

        initializeCollection(src);
        visited.add(src);

        while (!isCollectionEmpty()) {
            String current = getNextNode();

            if (current.equals(dst)) {
                return buildPath(src, dst);
            }

            for (DefaultEdge edge : graph.outgoingEdgesOf(current)) {
                String neighbor = graph.getEdgeTarget(edge).equals(current)
                        ? graph.getEdgeSource(edge)
                        : graph.getEdgeTarget(edge);

                if (!visited.contains(neighbor)) {
                    addToCollection(neighbor);
                    visited.add(neighbor);
                    parentMap.put(neighbor, current);
                }
            }
        }
        return null;
    }

    protected abstract void initializeCollection(String src);
    protected abstract boolean isCollectionEmpty();
    protected abstract String getNextNode();
    protected abstract void addToCollection(String node);
    protected abstract boolean isVisited(String node);
    protected abstract void markVisited(String node);


    private Path buildPath(String src, String dst) {
        Path path = new Path();
        for (String node = dst; node != null; node = parentMap.get(node)) {
            path.addNode(node);
        }
        Collections.reverse(path.getNodes());
        return path;
    }

}

