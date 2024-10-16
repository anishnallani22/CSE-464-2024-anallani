package org.example;

import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;
import guru.nidi.graphviz.model.Factory;
import guru.nidi.graphviz.model.MutableGraph;
import guru.nidi.graphviz.model.MutableNode;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import guru.nidi.graphviz.parse.Parser;
import java.io.File;
import java.io.IOException;

public class GraphGenerator {
    private org.jgrapht.Graph<String, DefaultEdge> graph;

    public GraphGenerator() {

        this.graph = new DefaultDirectedGraph<>(DefaultEdge.class);
    }

    public void parseGraph(String filepath) throws IOException {
        try {
            File dotFile = new File(filepath);
            MutableGraph parsedGraph = new Parser().read(dotFile);

            parsedGraph.nodes().forEach(node -> {
                String nodeName = node.name().toString();

                if (!graph.containsVertex(nodeName)) {
                    graph.addVertex(nodeName);
                }
                node.links().forEach(link -> {
                    String target = link.to().name().toString();

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

            Graphviz graphviz = Graphviz.fromGraph(convertToGraphvizGraph()).width(700);
            graphviz.render(Format.PNG).toFile(new File(outputFilePath));

            System.out.println("Graph image generated at: " + outputFilePath);
        } catch (Exception e) {
            System.out.println("Error generating graph image: " + e.getMessage());
            throw new IOException(e);
        }
    }


    private MutableGraph convertToGraphvizGraph() {
        MutableGraph g = Factory.mutGraph("Graph").setDirected(true);
        graph.vertexSet().forEach(node -> {
            MutableNode graphvizNode = Factory.mutNode(node);  // No need to cast
            g.add(graphvizNode);  // Add the node directly
        });
        graph.edgeSet().forEach(edge -> {
            String source = graph.getEdgeSource(edge);
            String target = graph.getEdgeTarget(edge);
            g.add(Factory.mutNode(source).addLink(Factory.mutNode(target)));
        });
        return g;
    }

    public org.jgrapht.Graph<String, DefaultEdge> getGraph() {
        return graph;
    }
}




