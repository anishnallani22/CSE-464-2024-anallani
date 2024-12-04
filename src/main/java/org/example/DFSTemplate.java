package org.example;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;

import java.util.Stack;

public class DFSTemplate extends GraphSearchTemplate implements GraphStrategyPattern {
    private Stack<String> stack;

    public DFSTemplate(Graph<String, DefaultEdge> graph) {
        super(graph);
    }

    @Override
    protected void initializeCollection(String src) {
        stack = new Stack<>();
        stack.push(src);
    }

    @Override
    protected boolean isCollectionEmpty() {
        return stack.isEmpty();
    }

    @Override
    protected String getNextNode() {
        return stack.pop();
    }

    @Override
    protected void addToCollection(String node) {
        stack.push(node);
    }


    @Override
    public Path search(Graph<String, DefaultEdge> graph, String src, String dst) {
        return super.search(src, dst);
    }
}


