package org.example;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;

import java.util.*;
import java.util.stream.Collectors;

public class RandomWalkSearchTemplate extends GraphSearchTemplate implements GraphStrategyPattern {

    private final Random random;
    private Stack<String> stack; // Collection for random walk
    private Set<String> visited; // Track visited nodes
    private Map<String, String> predecessors; // Store the path

    public RandomWalkSearchTemplate(Graph<String, DefaultEdge> graph) {
        super(graph);
        this.random = new Random();
    }

    @Override
    public Path search(Graph<String, DefaultEdge> graph, String src, String dst) {
        System.out.println("\nrandom testing");

        initializeCollection(src);

        while (!isCollectionEmpty()) {
            String current = getNextNode();
            // Print the path so far
            System.out.println("visiting " + constructPathSoFar(current));

            // Check if we've reached the destination
            if (current.equals(dst)) {
                Path finalPath = buildFinalPath(src, dst);
                // Print the final path
                System.out.println(constructPathSoFar(current));
                return finalPath;
            }

            List<String> neighbors = getUnvisitedNeighbors(graph, current);

            if (neighbors.isEmpty()) {
                System.out.println("No more unvisited neighbors. Random walk failed.");
                return null;
            }

            // Choose a random next node and update structures
            String nextNode = neighbors.get(random.nextInt(neighbors.size()));
            predecessors.put(nextNode, current); // Set predecessor for path building
            markVisited(nextNode);
            addToCollection(nextNode);
        }

        System.out.println("Random walk Path: No path found");
        return null;
    }

    private List<String> getUnvisitedNeighbors(Graph<String, DefaultEdge> graph, String node) {
        List<String> neighbors = new ArrayList<>();
        for (DefaultEdge edge : graph.outgoingEdgesOf(node)) {
            String neighbor = graph.getEdgeTarget(edge).equals(node)
                    ? graph.getEdgeSource(edge)
                    : graph.getEdgeTarget(edge);

            if (!visited.contains(neighbor)) {
                neighbors.add(neighbor);
            }
        }
        return neighbors;
    }

    @Override
    protected void initializeCollection(String src) {
        stack = new Stack<>();
        visited = new HashSet<>();
        predecessors = new HashMap<>();

        stack.push(src);
        visited.add(src);
        predecessors.put(src, null);
    }

    @Override
    protected boolean isCollectionEmpty() {
        return stack.isEmpty();
    }

    @Override
    protected String getNextNode() {
        return stack.pop();
    }

    @Override
    protected void addToCollection(String node) {
        stack.push(node);
    }

    @Override
    protected boolean isVisited(String node) {
        return visited.contains(node);
    }

    @Override
    protected void markVisited(String node) {
        visited.add(node);
    }

    // Helper method to construct the current path from source to the given current node
    private String constructPathSoFar(String current) {
        List<String> pathNodes = new LinkedList<>();
        String temp = current;

        while (temp != null) {
            pathNodes.add(0, temp);
            temp = predecessors.get(temp);
        }

        return "Path{nodes=[" + pathNodes.stream()
                .map(n -> "Node{" + n + "}")
                .collect(Collectors.joining(", ")) + "]}";
    }

    // Helper method to build the final path object
    private Path buildFinalPath(String src, String dst) {
        Path path = new Path();
        String current = dst;

        while (current != null) {
            path.addNode(current);
            current = predecessors.get(current);
        }

        Collections.reverse(path.getNodes()); // Start from the source in correct order
        return path;
    }

    @Override
    public Graph<String, DefaultEdge> getGraph() {
        return graph;
    }
}

