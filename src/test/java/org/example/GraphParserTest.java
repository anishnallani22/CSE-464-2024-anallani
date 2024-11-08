package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;
import java.util.NoSuchElementException;

public class GraphParserTest {

    // Test Feature 1: Parsing the graph from a DOT file
    @Test
    public void testParseGraph() throws IOException {
        String inputFilePath = "src/main/resources/input.dot";
        String expectedFilePath = "src/test/resources/expectedGraph.txt";

        GraphGenerator graphManager = new GraphGenerator();
        graphManager.parseGraph(inputFilePath);

        String actualOutput = graphManager.toString();
        String expectedOutput = Files.readString(Paths.get(expectedFilePath));

        assertEquals(expectedOutput.trim(), actualOutput.trim(), "The parsed graph does not match the expected output");
    }

    // Test Feature 2: Adding nodes
    @Test
    public void testAddNodes() throws IOException {
        String inputFilePath = "src/main/resources/input.dot";
        String expectedFilePath = "src/test/resources/expectedNodes.txt";

        GraphGenerator graphManager = new GraphGenerator();
        graphManager.parseGraph(inputFilePath);

        NodeModifier graphFeature2 = new NodeModifier(graphManager.getGraph());
        graphFeature2.addNode("i");
        String[] newNodes = {"j", "k", "l"};
        graphFeature2.addNodes(newNodes);

        String actualOutput = graphFeature2.printGraph();
        String expectedOutput = Files.readString(Paths.get(expectedFilePath));

        assertEquals(expectedOutput.trim(), actualOutput.trim(), "The nodes do not match the expected output after adding new nodes");
    }

    // Test Feature 3: Adding edges
    @Test
    public void testAddEdges() throws IOException {
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

        String actualOutput = graphFeature3.printGraph();
        String expectedOutput = Files.readString(Paths.get(expectedFilePath));

        assertEquals(expectedOutput.trim(), actualOutput.trim(), "The edges do not match the expected output after adding new edges");
    }

    // Test Feature 4: Output graph as a DOT file
    @Test
    public void testOutputDOTGraph() throws IOException {
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
    }

    //Test Feature 5: Remove single node
    @Test
    public void testRemoveNode() throws IOException {
        String inputFilePath = "src/main/resources/input.dot";
        GraphGenerator graphManager = new GraphGenerator();
        graphManager.parseGraph(inputFilePath);

        // Add nodes for verification
        NodeModifier nodeModifier = new NodeModifier(graphManager.getGraph());
        nodeModifier.addNode("i");
        String[] newNodes = {"j", "k", "l"};
        nodeModifier.addNodes(newNodes);

        // Ensure nodes are there to remove
        assertTrue(graphManager.getGraph().containsVertex("i"), "Node 'i' should exist before removal");
        assertTrue(graphManager.getGraph().containsVertex("j"), "Node 'j' should exist before removal");

        removeNode nodeRemover = new removeNode(graphManager.getGraph());
        // Scenario 1:Successfully remove an existing node
        nodeRemover.removeGraphNode("i");
        assertFalse(graphManager.getGraph().containsVertex("i"), "Node 'i' was not removed");

        // Scenario 2:Attempt to remove a non-existent node
        assertThrows(NoSuchElementException.class, () -> {
            nodeRemover.removeGraphNode("nonexistentNode");
        }, "Should throw NoSuchElementException");
    }

    //Test Feature 6: Removing multiple nodes



}

