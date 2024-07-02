package org.example;

import org.jgrapht.Graph;

public interface Algorithm {
    Path algorithmAction(String src, String dst, Graph graph);
}

class BFSAlgorithm implements Algorithm{
    @Override
    public Path algorithmAction(String src, String dst, Graph graph) {
        TraversalAlgorithm bfsTraversal = new BFSTraversal();
        return bfsTraversal.traverse(src, dst, graph);
    }
}

class DFSAlgorithm implements Algorithm{
    @Override
    public Path algorithmAction(String src, String dst, Graph graph) {
        TraversalAlgorithm dfsTraversal = new DFSTraversal();
        return dfsTraversal.traverse(src, dst, graph);
    }
}

class RandomWalkAlgorithm implements Algorithm{
    @Override
    public Path algorithmAction(String src, String dst, Graph graph) {
        TraversalAlgorithm randomWalkTraversal = new RandomWalkTraversal();
        return randomWalkTraversal.traverse(src, dst, graph);
    }
}
