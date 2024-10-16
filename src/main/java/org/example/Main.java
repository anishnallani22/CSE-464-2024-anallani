package org.example;
import guru.nidi.graphviz.parse.Parser;
import guru.nidi.graphviz.model.MutableGraph;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        // Path to your input DOT file in the resources directory
        String inputFilePath = "src/main/resources/input.dot";  // Update the path
        // Path to where the output PNG file should be saved in the resources directory
        String outputFilePath = "src/main/resources/input.png";  // Update the path

        try {
            // Create an instance of GraphGenerator
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
