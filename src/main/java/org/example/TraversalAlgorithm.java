package org.example;

import org.jgrapht.Graph;

public abstract class TraversalAlgorithm {
    public Algorithm algorithmType;    // Instance variable that's the subclass of the Algorithm interface, composition.

    final Path traverse(String src, String dst, Graph graph){
        String node = src;

        while(node != null){
            visitNode(graph, node, dst);
            node = getNextNode(graph, node, dst);
        }

        Path path = getPath(dst);
        return path;
    }

    abstract String getNextNode(Graph graph, String node, String dst);
    abstract void visitNode(Graph graph, String node, String dst);
    abstract Path getPath(String dst);

    public Path algorithmAction(String src, String dst, Graph graph){  // Actually implements the algorithm in play on the object.
        return algorithmType.algorithmAction(src, dst, graph);
    }

    public void setAlgorithmType(Algorithm newAlgorithmType){   // Allows for setting its algorithm type to a specified type.
        algorithmType = newAlgorithmType;
    }
}