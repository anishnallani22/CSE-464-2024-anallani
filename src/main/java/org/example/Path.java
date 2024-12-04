package org.example;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;

import java.util.ArrayList;
import java.util.List;

public class Path {
    private List<String> nodes;

    public Path() {
        this.nodes = new ArrayList<>();
    }

    public void addNode(String node) {
        nodes.add(node);
    }

    @Override
    public String toString() {
        return String.join(" -> ", nodes);
    }

    //Strategy Pattern
    public static Path GraphSearch(String src, String dst, GraphStrategyPattern strategy) {
        if (!strategy.getGraph().containsVertex(src) || !strategy.getGraph().containsVertex(dst)) {
            return null;
        }

        return strategy.search(strategy.getGraph(), src, dst);
    }





    // Get the list of nodes in the path
    public List<String> getNodes() {
        return nodes;
    }
}





