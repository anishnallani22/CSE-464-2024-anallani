package org.example;
import guru.nidi.graphviz.parse.Parser;
import guru.nidi.graphviz.model.MutableGraph;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import java.io.File;
import java.io.IOException;
public class Main {
    public static void main(String[] args) {
        // Path to your input DOT file
        String inputFilePath = "/Users/anishnallani/Documents/CSE464/input.dot";
        // Path to where the output PNG file should be saved
        String outputFilePath = "/Users/anishnallani/Documents/CSE464/input.png";
        String testMessage = "test";

        try {
            // Create an instance of GraphGenerator (GraphManager in this case)
            GraphGenerator graphManager = new GraphGenerator();

            // Parse the DOT file to create the graph
            graphManager.parseGraph(inputFilePath);

            // Print the graph details (this will test if the parsing works correctly)
            System.out.println(graphManager.toString());

            // Output the graph as a PNG image file
            graphManager.outputGraph(outputFilePath);
            System.out.println("Graph image generated at: " + outputFilePath);

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
