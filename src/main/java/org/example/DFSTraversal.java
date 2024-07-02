package org.example;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;

import java.util.Set;
import java.util.Stack;

public class DFSTraversal extends TraversalAlgorithm{
    public DFSTraversal(){  // Public constructor that sets the action to DFS from the algorithm.
        super();
        algorithmType = new DFSAlgorithm();
    }
    private Stack<String> discovered = new Stack<String>();
    Path path = new Path();

    @Override
    String getNextNode(Graph graph, String node, String dst){
        if(node.equals(dst)){
            return null;
        }

        if(path.size() > 0){    // Handling null exception with if condition.
            while(!graph.containsEdge(path.last(), discovered.peek())){ // Pruning path.
                path.remove();
            }
        }

        return discovered.peek();
    }

    @Override
    void visitNode(Graph graph, String node, String dst) {  // Marks the current node and its connected components.
        if(!discovered.contains(node)){
            discovered.push(node);
        }

        path.add(discovered.pop());

        Set<DefaultEdge> edges = graph.outgoingEdgesOf(node);
        String temp = "";
        for(DefaultEdge item : edges){  // Iterate over the set of edges to add them to stack.
            temp = graph.getEdgeTarget(item).toString();
            discovered.push(temp);
        }
    }

    @Override
    Path getPath(String dst){
        return path;
    }
}
