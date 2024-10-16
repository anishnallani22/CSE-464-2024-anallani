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
        String outputFilePath1 = "src/main/resources/output1.png";
        String outputFilePath2 = "src/main/resources/output2.png";
        String outputFilePath4 = "src/main/resources/output4.png";
        String outputDotPath = "src/main/resources/outputGraph.dot";

        try {
            GraphGenerator graphManager = new GraphGenerator();
            graphManager.parseGraph(inputFilePath);

            graphManager.outputGraph(outputFilePath1);
            System.out.println("Original graph image generated at: " + outputFilePath1);

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

            outputGraph outputFeature4 = new outputGraph(graphManager.getGraph());
            outputFeature4.outputDOTGraph(outputDotPath);
            outputFeature4.outputGraphics(outputFilePath4, "png");

            System.out.println("Final graph image generated at: " + outputFilePath4);
            System.out.println("DOT file generated at: " + outputDotPath);

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}




