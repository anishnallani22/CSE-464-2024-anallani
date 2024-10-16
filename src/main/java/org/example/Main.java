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
        String inputFilePath = "src/main/resources/input.dot";  // Path to DOT file
        String outputFilePath = "src/main/resources/output1.png";  // Path for original graph PNG
        String outputFilePath2 = "src/main/resources/output2.png";  // Path for updated graph PNG
        String outputFilePath3 = "src/main/resources/output3.png";  // Path for updated graph PNG (Feature 3)

        try {

            GraphGenerator graphManager = new GraphGenerator();
            graphManager.parseGraph(inputFilePath);

            System.out.println("Original Graph:");
            System.out.println(graphManager.toString());

            graphManager.outputGraph(outputFilePath);
            System.out.println("Graph image generated at: " + outputFilePath);

            NodeModifier graphFeature2 = new NodeModifier(graphManager.getGraph());
            graphFeature2.addNode("i");
            String[] newNodes = {"j", "k", "l"};
            graphFeature2.addNodes(newNodes);

            System.out.println(graphFeature2.printGraph());


            graphManager.outputGraph(outputFilePath2);
            System.out.println("Updated graph image generated at: " + outputFilePath2);


            addEdges graphFeature3 = new addEdges(graphManager.getGraph());
            graphFeature3.addEdge("a", "i");
            graphFeature3.addEdge("b", "j");
            graphFeature3.addEdge("c", "k");
            graphFeature3.addEdge("d", "l");


            System.out.println(graphFeature3.printGraph());


            graphManager.outputGraph(outputFilePath3);
            System.out.println("Updated graph image generated at: " + outputFilePath3);

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}



