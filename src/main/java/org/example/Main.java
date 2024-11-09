package org.example;

import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import java.util.NoSuchElementException;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        String inputFilePath = "src/main/resources/input.dot";
        String outputFilePath1 = "src/main/resources/output1.png";
        String outputDotPath = "src/main/resources/outputGraph.dot";

        try {
            // Step 1: Parse the initial graph from the DOT file
            GraphGenerator graphManager = new GraphGenerator();
            graphManager.parseGraph(inputFilePath);

            // Generate the original graph image
            graphManager.outputGraph(outputFilePath1);
            System.out.println("Original graph image generated at: " + outputFilePath1);

            // Step 2: Perform BFS Search
            System.out.println("\n=== Performing BFS Search ===");
            Path bfsPath = Path.GraphSearchBFS(graphManager.getGraph(), "a", "g");
            if (bfsPath != null) {
                System.out.println("BFS Path found: " + bfsPath);
            } else {
                System.out.println("No path found using BFS.");
            }

            // Step 3: Generate final graph output after modifications
            outputGraph outputFeature = new outputGraph(graphManager.getGraph());
            outputFeature.outputDOTGraph(outputDotPath);
            outputFeature.outputGraphics(outputFilePath1, "png");

            System.out.println("Final graph image generated at: " + outputFilePath1);
            System.out.println("DOT file generated at: " + outputDotPath);

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (NoSuchElementException e) {
            System.out.println("Error: One or both nodes do not exist for the edge removal.");
        }
    }
}









