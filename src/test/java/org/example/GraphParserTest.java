package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

@TestMethodOrder(OrderAnnotation.class)
public class GraphParserTest {

    // Test Feature 1: Parsing the graph from a DOT file
    @Test
    @Order(1)
    public void testParseGraph() throws IOException {
        System.out.println("\n=== Testing Feature 1: Parsing the Graph ===");
        String inputFilePath = "src/main/resources/input.dot";
        String expectedFilePath = "src/test/resources/expectedGraph.txt";

        GraphGenerator graphManager = new GraphGenerator();
        graphManager.parseGraph(inputFilePath);

        String actualOutput = graphManager.toString();
        String expectedOutput = Files.readString(Paths.get(expectedFilePath));

        assertEquals(expectedOutput.trim(), actualOutput.trim(), "The parsed graph does not match the expected output");
        System.out.println("");
    }

    // Test Feature 2: Adding nodes
    @Test
    @Order(2)
    public void testAddNodes() throws IOException {
        System.out.println("\n=== Testing Feature 2: Adding Nodes ===");
        String inputFilePath = "src/main/resources/input.dot";
        String expectedFilePath = "src/test/resources/expectedNodes.txt";

        GraphGenerator graphManager = new GraphGenerator();
        graphManager.parseGraph(inputFilePath);

        NodeModifier graphFeature2 = new NodeModifier(graphManager.getGraph());
        graphFeature2.addNode("i");
        String[] newNodes = {"j", "k", "l"};
        graphFeature2.addNodes(newNodes);

        String actualOutput = GraphPrintingUtility.printGraph(graphManager.getGraph());

        String expectedOutput = Files.readString(Paths.get(expectedFilePath));

        assertEquals(expectedOutput.trim(), actualOutput.trim(), "The nodes do not match the expected output after adding new nodes");
        System.out.println("");
    }

    // Test Feature 3: Adding edges
    @Test
    @Order(3)
    public void testAddEdges() throws IOException {
        System.out.println("\n=== Testing Feature 3: Adding Edges ===");
        String inputFilePath = "src/main/resources/input.dot";
        String expectedFilePath = "src/test/resources/expectedEdges.txt";

        GraphGenerator graphManager = new GraphGenerator();
        graphManager.parseGraph(inputFilePath);


        NodeModifier graphFeature2 = new NodeModifier(graphManager.getGraph());
        graphFeature2.addNode("i");
        String[] newNodes = {"j", "k", "l"};
        graphFeature2.addNodes(newNodes);


        addEdges graphFeature3 = new addEdges(graphManager.getGraph());
        graphFeature3.addEdge("a", "i");
        graphFeature3.addEdge("b", "j");
        graphFeature3.addEdge("c", "k");
        graphFeature3.addEdge("d", "l");

        String actualOutput = GraphPrintingUtility.printGraph(graphManager.getGraph());
        String expectedOutput = Files.readString(Paths.get(expectedFilePath));

        assertEquals(expectedOutput.trim(), actualOutput.trim(), "The edges do not match the expected output after adding new edges");
        System.out.println("");
    }

    // Test Feature 4: Output graph as a DOT file
    @Test
    @Order(4)
    public void testOutputDOTGraph() throws IOException {
        System.out.println("\n=== Testing Feature 4: Outputting Graph as DOT File ===");
        String inputFilePath = "src/main/resources/input.dot";
        String expectedDotFilePath = "src/test/resources/expectedGraph.dot";
        String actualDotFilePath = "src/main/resources/outputGraph.dot";

        GraphGenerator graphManager = new GraphGenerator();
        graphManager.parseGraph(inputFilePath);


        NodeModifier graphFeature2 = new NodeModifier(graphManager.getGraph());
        graphFeature2.addNode("i");
        String[] newNodes = {"j", "k", "l"};
        graphFeature2.addNodes(newNodes);

        addEdges graphFeature3 = new addEdges(graphManager.getGraph());
        graphFeature3.addEdge("a", "i");
        graphFeature3.addEdge("b", "j");
        graphFeature3.addEdge("c", "k");
        graphFeature3.addEdge("d", "l");


        outputGraph outputFeature4 = new outputGraph(graphManager.getGraph());
        outputFeature4.outputDOTGraph(actualDotFilePath);

        String actualOutput = Files.readString(Paths.get(actualDotFilePath));
        String expectedOutput = Files.readString(Paths.get(expectedDotFilePath));

        assertEquals(expectedOutput.trim(), actualOutput.trim(), "The DOT file output does not match the expected output");
        System.out.println("");
    }

    //Test Feature 5: Remove single node
    @Test
    @Order(5)
    public void testRemoveNode() throws IOException {
        System.out.println("\n=== Testing Feature 5: Removing a Single Node ===");
        String inputFilePath = "src/main/resources/input.dot";
        String outputPngFilePath = "src/main/resources/output5.png";

        GraphGenerator graphManager = new GraphGenerator();
        graphManager.parseGraph(inputFilePath);

        NodeModifier nodeModifier = new NodeModifier(graphManager.getGraph());
        nodeModifier.addNode("i");
        String[] newNodes = {"j", "k", "l"};
        nodeModifier.addNodes(newNodes);

        removeNode nodeRemover = new removeNode(graphManager.getGraph());
        nodeRemover.removeNode("i");

        // Generate the output DOT file and PNG
        outputGraph outputFeature = new outputGraph(graphManager.getGraph());
        outputFeature.outputGraphics(outputPngFilePath, "png");


    }


    //Test Feature 6: Removing multiple nodes
    @Test
    @Order(6)
    public void testRemoveMultipleNodes() throws IOException {
        System.out.println("\n=== Testing Feature 6: Removing Multiple Nodes ===");
        String inputFilePath = "src/main/resources/input.dot";
        String outputPngFilePath = "src/main/resources/output6.png";

        GraphGenerator graphManager = new GraphGenerator();
        graphManager.parseGraph(inputFilePath);

        NodeModifier nodeModifier = new NodeModifier(graphManager.getGraph());
        nodeModifier.addNode("i");
        String[] newNodes = {"j", "k", "l"};
        nodeModifier.addNodes(newNodes);

        addEdges graphFeature3 = new addEdges(graphManager.getGraph());
        graphFeature3.addEdge("a", "i");
        graphFeature3.addEdge("b", "j");

        removeMultipleNodes nodeRemover = new removeMultipleNodes(graphManager.getGraph());
        String[] nodesToRemove = {"i", "j"};
        nodeRemover.removeNodes(nodesToRemove);

        // Generate the output DOT file and PNG
        outputGraph outputFeature = new outputGraph(graphManager.getGraph());
        outputFeature.outputGraphics(outputPngFilePath, "png");

    }


    //Test Feature 7: Removing an edge
    @Test
    @Order(7)
    public void testRemoveEdge() throws IOException {
        System.out.println("\n=== Testing Feature 7: Removing an Edge ===");
        String inputFilePath = "src/main/resources/input.dot";
        String outputPngFilePath = "src/main/resources/output7.png";

        GraphGenerator graphManager = new GraphGenerator();
        graphManager.parseGraph(inputFilePath);

        NodeModifier nodeModifier = new NodeModifier(graphManager.getGraph());
        nodeModifier.addNode("i");
        String[] newNodes = {"j", "k", "l"};
        nodeModifier.addNodes(newNodes);

        addEdges graphFeature3 = new addEdges(graphManager.getGraph());
        graphFeature3.addEdge("a", "i");
        graphFeature3.addEdge("b", "j");

        removeEdge edgeRemover = new removeEdge(graphManager.getGraph());
        edgeRemover.removeEdge("a", "i");

        // Generate the output DOT file and PNG
        outputGraph outputFeature = new outputGraph(graphManager.getGraph());
        outputFeature.outputGraphics(outputPngFilePath, "png");

    }
}

