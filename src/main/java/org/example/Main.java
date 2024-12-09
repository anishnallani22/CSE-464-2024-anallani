package org.example;

import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import java.util.NoSuchElementException;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        String inputFilePath = "src/main/resources/input.dot";
        String outputFilePath1 = "src/main/resources/output1.png";
        String outputFilePath2 = "src/main/resources/output2.png";
        String outputFilePath4 = "src/main/resources/output4.png";
        String outputDotPath = "src/main/resources/outputGraph.dot";

        try {
            // Step 1: Parse the initial graph from the DOT file
            GraphGenerator graphManager = new GraphGenerator();
            graphManager.parseGraph(inputFilePath);


            // Generate the original graph image
            graphManager.outputGraph(outputFilePath1);
            System.out.println("Original graph image generated at: " + outputFilePath1);

            // Step 2: Add nodes using NodeModifier
            NodeModifier graphFeature2 = new NodeModifier(graphManager.getGraph());
            graphFeature2.addNode("i");
            String[] newNodes = {"j", "k", "l"};
            graphFeature2.addNodes(newNodes);
            System.out.println("\nNodes added:");
            System.out.println(GraphPrintingUtility.printGraph(graphManager.getGraph()));

            // Generate the updated graph image after adding nodes
            graphManager.outputGraph(outputFilePath2);
            System.out.println("Updated graph image generated at: " + outputFilePath2);

            // Step 3: Add edges using addEdges
            addEdges graphFeature3 = new addEdges(graphManager.getGraph());
            graphFeature3.addEdge("a", "i");
            graphFeature3.addEdge("b", "j");
            graphFeature3.addEdge("c", "k");
            graphFeature3.addEdge("d", "l");
            System.out.println("\nEdges added:");
            System.out.println(GraphPrintingUtility.printGraph(graphManager.getGraph()));

            // Step 4: Perform BFS search using Algorithm Enum and Strategy Pattern
            System.out.println("\n=== Performing BFS Search ===");
            Path bfsPath = GraphAlgorithmContext.GraphSearch(graphManager.getGraph(), "a", "l", Algorithm.BFS);
            if (bfsPath != null) {
                System.out.println("Path found using BFS: " + bfsPath);
            } else {
                System.out.println("No path found using BFS");
            }

            // Step 5: Perform DFS search using Algorithm Enum and Strategy Pattern
            System.out.println("\n=== Performing DFS Search ===");
            Path dfsPath = GraphAlgorithmContext.GraphSearch(graphManager.getGraph(), "a", "l", Algorithm.DFS);
            if (dfsPath != null) {
                System.out.println("Path found using DFS: " + dfsPath);
            } else {
                System.out.println("No path found using DFS");
            }


            // Step 6: Generate the final graph output
            outputGraph outputFeature4 = new outputGraph(graphManager.getGraph());
            outputFeature4.outputDOTGraph(outputDotPath);
            outputFeature4.outputGraphics(outputFilePath4, "png");
            System.out.println("Final graph image generated at: " + outputFilePath4);
            System.out.println("DOT file generated at: " + outputDotPath);

            // Step 7: Perform Random Walk Search
            System.out.println("\n=== Performing Random Walk Search ===");
            GraphStrategyPattern randomWalkStrategy = new RandomWalkSearchTemplate(graphManager.getGraph());

            for (int i = 0; i < 10; i++) {
                Path randomWalkPath = Path.GraphSearch("a", "c", randomWalkStrategy);
                if (randomWalkPath != null) {
                    System.out.println("Path{nodes=" + randomWalkPath.getNodes() + "}");
                }
            }





        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (NoSuchElementException e) {
            System.out.println("Error: One or both nodes do not exist.");
        }
    }
}


