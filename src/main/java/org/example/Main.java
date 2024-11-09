package org.example;

import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import java.util.NoSuchElementException;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        String inputFilePath = "src/main/resources/input.dot";
        String outputFilePath1 = "src/main/resources/output1.png";
        String outputFilePath4 = "src/main/resources/output4.png";
        String outputDotPath = "src/main/resources/outputGraph.dot";

        try {
            // Parse the initial graph from the DOT file
            GraphGenerator graphManager = new GraphGenerator();
            graphManager.parseGraph(inputFilePath);

            // Generate the original graph image
            graphManager.outputGraph(outputFilePath1);
            System.out.println("Original graph image generated at: " + outputFilePath1);

            // Perform BFS search
            Path bfsPath = Path.GraphSearch(graphManager.getGraph(), "a", "l", Algorithm.BFS);
            if (bfsPath != null) {
                System.out.println("Path found using BFS: " + bfsPath);
            } else {
                System.out.println("No path found using BFS");
            }

            // Perform DFS search
            Path dfsPath = Path.GraphSearch(graphManager.getGraph(), "a", "l", Algorithm.DFS);
            if (dfsPath != null) {
                System.out.println("Path found using DFS: " + dfsPath);
            } else {
                System.out.println("No path found using DFS");
            }

            // Generate final graph output
            outputGraph outputFeature4 = new outputGraph(graphManager.getGraph());
            outputFeature4.outputDOTGraph(outputDotPath);
            outputFeature4.outputGraphics(outputFilePath4, "png");

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (NoSuchElementException e) {
            System.out.println("Error: One or both nodes do not exist for the edge removal.");
        }
    }
}

