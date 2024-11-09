package org.example;

import java.util.LinkedList;
import java.util.List;

public class Path {
    private List<String> nodes;

    // Constructor
    public Path() {
        this.nodes = new LinkedList<>();
    }

    // Add a node to the path
    public void addNode(String node) {
        nodes.add(node);
    }


    public List<String> getNodes() {
        return nodes;
    }

    public int getLength() {
        return nodes.size();
    }

    public boolean isEmpty() {
        return nodes.isEmpty();
    }


    @Override
    public String toString() {
        return String.join(" -> ", nodes);
    }
}
