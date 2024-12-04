package org.example;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;

import java.util.LinkedList;
import java.util.Queue;

public class BFSTemplate extends GraphSearchTemplate {
    private Queue<String> queue;

    public BFSTemplate(Graph<String, DefaultEdge> graph) {
        super(graph);
    }

    @Override
    protected void initializeCollection(String src) {
        queue = new LinkedList<>();
        queue.add(src);
    }

    @Override
    protected boolean isCollectionEmpty() {
        return queue.isEmpty();
    }

    @Override
    protected String getNextNode() {
        return queue.poll();
    }

    @Override
    protected void addToCollection(String node) {
        queue.add(node);
    }
}

