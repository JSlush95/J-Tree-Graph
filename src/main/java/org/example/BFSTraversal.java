package org.example;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class BFSTraversal extends TraversalAlgorithm{
    public BFSTraversal(){ // Public constructor that sets the action to BFS from the algorithm.
        super();
        algorithmType = new BFSAlgorithm();
    }

    private LinkedList<LinkedList<String>> discovered = new LinkedList<LinkedList<String>>();
    private Queue<String> nextNodes = new LinkedList<String>();

    Path path = new Path();

    @Override
    String getNextNode(Graph graph, String node, String dst){
        if(node.equals(dst)){
            return null;
        }

        return nextNodes.poll();
    }

    @Override
    void visitNode(Graph graph, String node, String dst) {  // Marks the current node and its connected components.
        if(discovered.isEmpty()){
            discovered.add(new LinkedList<String>(Collections.singleton(node)));
        }

        Set<DefaultEdge> edges = graph.outgoingEdgesOf(node);
        String temp = "";
        LinkedList<String> base = null;
        LinkedList<String> newPath = null;

        for(LinkedList<String> link : discovered){
            if(link.contains(node)){
                base = link;
            }
        }

        for(DefaultEdge item : edges){  // Iterate over the set of edges to add them to stack.
            newPath = new LinkedList<String>(base);
            temp = graph.getEdgeTarget(item).toString();
            newPath.addLast(temp);
            discovered.add(newPath);
            nextNodes.add(temp);
        }
    }

    @Override
    Path getPath(String dst){
        LinkedList<String> base = null;

        for(LinkedList<String> link : discovered){
            if(link.contains(dst)){
                base = link;
            }
        }

        for(String item : base){
            path.add(item);
        }

        return path;
    }
}

