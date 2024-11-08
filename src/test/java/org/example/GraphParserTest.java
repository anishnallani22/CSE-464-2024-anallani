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

        String actualOutput = graphFeature2.printGraph();
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

        String actualOutput = graphFeature3.printGraph();
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

        // Scenario 2: Test to remove a non-existent node
        assertThrows(NoSuchElementException.class, () -> {
            nodeRemover.removeGraphNode("nonexistentNode");
        }, "Should throw NoSuchElementException");
        System.out.println("");
    }

    //Test Feature 6: Removing multiple nodes
    @Test
    @Order(6)
    public void testRemoveMultipleNodes() throws IOException {
        System.out.println("\n=== Testing Feature 6: Removing Multiple Nodes ===");
        String inputFilePath = "src/main/resources/input.dot";
        GraphGenerator graphManager = new GraphGenerator();
        graphManager.parseGraph(inputFilePath);

        // Add nodes for verification
        NodeModifier nodeModifier = new NodeModifier(graphManager.getGraph());
        nodeModifier.addNode("i");
        String[] newNodes = {"j", "k", "l"};
        nodeModifier.addNodes(newNodes);

        //Add edges to establish connection between nodes
        addEdges graphFeature3 = new addEdges(graphManager.getGraph());
        graphFeature3.addEdge("a", "i");
        graphFeature3.addEdge("b", "j");

        // Ensure nodes and edges exist
        assertTrue(graphManager.getGraph().containsVertex("i"), "Node 'i' should exist before removal");
        assertTrue(graphManager.getGraph().containsVertex("j"), "Node 'j' should exist before removal");
        assertTrue(graphManager.getGraph().containsEdge("a", "i"), "Edge 'a -> i' should exist before removal");
        assertTrue(graphManager.getGraph().containsEdge("b", "j"), "Edge 'b -> j' should exist before removal");

        // Initialize the new class for removing multiple nodes
        removeMultipleNodes nodeRemover = new removeMultipleNodes(graphManager.getGraph());

        // Scenario 1: Successfully remove existing nodes
        String[] nodesToRemove = {"i", "j"};
        nodeRemover.removeNodes(nodesToRemove);

        // Verify that nodes and their associated edges are removed
        assertFalse(graphManager.getGraph().containsVertex("i"), "Node 'i' was not removed");
        assertFalse(graphManager.getGraph().containsVertex("j"), "Node 'j' was not removed");
        assertFalse(graphManager.getGraph().containsEdge("a", "i"), "Edge 'a -> i' was not removed");
        assertFalse(graphManager.getGraph().containsEdge("b", "j"), "Edge 'b -> j' was not removed");

        // Scenario 2: Test to remove non-existent nodes
        String[] nonExistentNodes = {"x", "y"};
        assertThrows(NoSuchElementException.class, () -> {
            nodeRemover.removeNodes(nonExistentNodes);
        }, "Should throw NoSuchElementException when removing non-existent nodes");
        System.out.println("");
    }

    //Test Feature 7: Removing an edge
    @Test
    @Order(7)
    public void testRemoveEdge() throws IOException {
        System.out.println("\n=== Testing Feature 7: Removing an Edge ===");
        String inputFilePath = "src/main/resources/input.dot";
        GraphGenerator graphManager = new GraphGenerator();
        graphManager.parseGraph(inputFilePath);

        // Add nodes
        NodeModifier nodeModifier = new NodeModifier(graphManager.getGraph());
        nodeModifier.addNode("i");
        String[] newNodes = {"j", "k", "l"};
        nodeModifier.addNodes(newNodes);

        // Add edges
        addEdges graphFeature3 = new addEdges(graphManager.getGraph());
        graphFeature3.addEdge("a", "i");
        graphFeature3.addEdge("b", "j");
        graphFeature3.addEdge("c", "k");

        // Ensure the edges exist
        assertTrue(graphManager.getGraph().containsEdge("a", "i"), "Edge 'a -> i' should exist before removal");
        assertTrue(graphManager.getGraph().containsEdge("b", "j"), "Edge 'b -> j' should exist before removal");


        removeEdge edgeRemover = new removeEdge(graphManager.getGraph());

        //Scenario 1: Successfully remove existing edges
        edgeRemover.removeEdge("a", "i");
        edgeRemover.removeEdge("b", "j");

        //Verify edges are removed
        assertFalse(graphManager.getGraph().containsEdge("a", "i"), "Edge 'a -> i' was not removed");
        assertFalse(graphManager.getGraph().containsEdge("b", "j"), "Edge 'b -> j' was not removed");

        //Scenario 2: Attempt to remove a non-existent edge
        assertThrows(NoSuchElementException.class, () -> {
            edgeRemover.removeEdge("x", "y");
        }, "Should throw NoSuchElementException when removing non-existent edge");
        System.out.println("");
    }

}

