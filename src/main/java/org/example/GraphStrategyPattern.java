package org.example;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;

public interface GraphStrategyPattern {

    Path search(Graph<String, DefaultEdge> graph, String src, String dst);


    Graph<String, DefaultEdge> getGraph();
}






