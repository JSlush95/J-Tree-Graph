package org.example;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;

import java.util.*;

public class RandomWalkTraversal extends TraversalAlgorithm {
    public RandomWalkTraversal(){ // Public constructor that sets the action to random walk from the algorithm.
        super();
        algorithmType = new RandomWalkAlgorithm();
    }

    private Stack<String> discovered = new Stack<String>();
    Path path = new Path();

    @Override
    String getNextNode(Graph graph, String node, String dst){
        if(node.equals(dst)){
            return null;
        }

        if(discovered.isEmpty()){
            path.emptyStack();
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
        ArrayList<DefaultEdge> edgesList = new ArrayList<DefaultEdge>(edges);   // Converting set to arraylist to use indices for random generated choice.
        int numChildren = edgesList.size(); // Codifying the upper bound for the children.
        Random rand = new Random(); // Creating a new random object.
        DefaultEdge item = null;
        int randNum = 0;
        if(numChildren == 0){
            return;
        }else{
            while(numChildren > 0){
                randNum = rand.nextInt(0, numChildren);    // Creating a random number of the range.
                item = edgesList.get(randNum);   // Getting the index of the randomly generated int.
                String temp = "";
                temp = graph.getEdgeTarget(item).toString();
                discovered.push(temp);
                edgesList.remove(graph.getEdge(node, temp));
                numChildren = edgesList.size(); // Codifying the upper bound for the children.
            }
        }
    }

    @Override
    Path getPath(String dst){
        return path;
    }
}
