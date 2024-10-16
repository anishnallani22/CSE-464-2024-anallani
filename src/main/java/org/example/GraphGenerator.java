package org.example;

import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;
import guru.nidi.graphviz.parse.Parser;
import guru.nidi.graphviz.model.MutableGraph;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import java.io.File;
import java.io.IOException;

public class GraphGenerator {
    private org.jgrapht.Graph<String, DefaultEdge> graph;

    public GraphGenerator() {
        // Initialize the graph as a directed graph using JGraphT
        this.graph = new DefaultDirectedGraph<>(DefaultEdge.class);
    }

    // Feature 1: Parse a DOT file and create a graph object
    public void parseGraph(String filepath) throws IOException {
        try {
            // Use Graphviz-java's Parser to read the DOT file
            File dotFile = new File(filepath);
            MutableGraph parsedGraph = new Parser().read(dotFile);  // Use MutableGraph for parsing

            // Populate the JGraphT graph from the parsed Graphviz graph
            parsedGraph.nodes().forEach(node -> {
                String nodeName = node.name().toString();
                // Ensure the node exists in the graph before adding edges
                if (!graph.containsVertex(nodeName)) {
                    graph.addVertex(nodeName);
                }
                node.links().forEach(link -> {
                    String target = link.to().name().toString();
                    // Ensure both source and target nodes exist before adding the edge
                    if (!graph.containsVertex(target)) {
                        graph.addVertex(target);
                    }
                    graph.addEdge(nodeName, target);
                });
            });

        } catch (Exception e) {
            System.out.println("Error parsing DOT file: " + e.getMessage());
            throw new IOException(e);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Graph Details:\n");
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

    public void outputGraph(String outputFilePath) throws IOException {
        try {
            // Render the graph that was already parsed, and save it as a PNG image
            Graphviz.fromGraph(new Parser().read(new File("src/main/resources/input.dot")))  // Use the same relative path
                    .width(700)
                    .render(Format.PNG)
                    .toFile(new File(outputFilePath));

            System.out.println("Graph image generated at: " + outputFilePath);
        } catch (Exception e) {
            System.out.println("Error generating graph image: " + e.getMessage());
            throw new IOException(e);
        }
    }

}

