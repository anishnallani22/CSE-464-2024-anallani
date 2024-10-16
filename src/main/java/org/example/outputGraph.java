package org.example;

import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;
import guru.nidi.graphviz.model.Factory;
import guru.nidi.graphviz.model.MutableGraph;
import org.jgrapht.graph.DefaultEdge;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class outputGraph {
    private org.jgrapht.Graph<String, DefaultEdge> graph;

    public outputGraph(org.jgrapht.Graph<String, DefaultEdge> graph) {
        this.graph = graph;
    }

    public void outputDOTGraph(String path) throws IOException {
        try (FileWriter fileWriter = new FileWriter(path);
             PrintWriter printWriter = new PrintWriter(fileWriter)) {

            // Write the DOT representation of the graph without quotes
            printWriter.println("digraph G {");
            graph.edgeSet().forEach(edge -> {
                String source = graph.getEdgeSource(edge);
                String target = graph.getEdgeTarget(edge);
                printWriter.printf("  %s -> %s;%n", source, target);  // No quotes around source and target
            });
            printWriter.println("}");
            System.out.println("Graph successfully written to DOT file without quotes: " + path);
        } catch (IOException e) {
            System.out.println("Error writing DOT file: " + e.getMessage());
            throw e;
        }
    }


    public void outputGraphics(String path, String format) throws IOException {
        try {
            MutableGraph graphvizGraph = convertToGraphvizGraph();

            if (format.equalsIgnoreCase("png")) {
                Graphviz.fromGraph(graphvizGraph)
                        .width(700)
                        .render(Format.PNG)
                        .toFile(new File(path));
                System.out.println("Graph successfully written as PNG image: " + path);
            } else {
                System.out.println("Unsupported format: " + format);
            }
        } catch (IOException e) {
            System.out.println("Error generating graph image: " + e.getMessage());
            throw e;
        }
    }

    private MutableGraph convertToGraphvizGraph() {
        MutableGraph g = Factory.mutGraph("Graph").setDirected(true);
        graph.vertexSet().forEach(node -> {
            g.add(Factory.mutNode(node));
        });
        graph.edgeSet().forEach(edge -> {
            String source = graph.getEdgeSource(edge);
            String target = graph.getEdgeTarget(edge);
            g.add(Factory.mutNode(source).addLink(Factory.mutNode(target)));
        });
        return g;
    }
}

