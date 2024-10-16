package org.example;
import guru.nidi.graphviz.model.Node;
import guru.nidi.graphviz.parse.Parser;
import guru.nidi.graphviz.model.MutableGraph;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import java.io.File;
import java.io.IOException;


public class Main {
    public static void main(String[] args) {

        String inputFilePath = "src/main/resources/input.dot";
        String outputFilePath = "src/main/resources/output.png";
        String outputFilePath2 = "src/main/resources/output2.png";

        try {

            GraphGenerator graphManager = new GraphGenerator();
            graphManager.parseGraph(inputFilePath);


            System.out.println("Original Graph:");
            System.out.println(graphManager.toString());


            graphManager.outputGraph(outputFilePath);
            System.out.println("Graph image generated at: " + outputFilePath);

            // Step 2: Add nodes using Feature 2
            NodeModifier graphFeature2 = new NodeModifier(graphManager.getGraph());
            graphFeature2.addNode("i");
            String[] newNodes = {"j", "k", "l"};
            graphFeature2.addNodes(newNodes);


            System.out.println(graphFeature2.printGraph());


            graphManager.outputGraph(outputFilePath2);
            System.out.println("Updated graph image generated at: " + outputFilePath2);

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}

