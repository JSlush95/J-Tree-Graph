package org.example;

import guru.nidi.graphviz.attribute.Color;
import guru.nidi.graphviz.attribute.Style;
import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;
import guru.nidi.graphviz.model.MutableGraph;

import guru.nidi.graphviz.parse.Parser;
import org.apache.commons.lang.StringUtils;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.nio.ExportException;
import org.jgrapht.nio.ImportException;
import org.jgrapht.nio.dot.DOTExporter;
import org.jgrapht.nio.dot.DOTImporter;
import org.jgrapht.util.SupplierUtil;

import java.io.*;
import java.util.Arrays;

public class GraphManager {
    public enum Algorithm{
        bfs,
        dfs,
        randWalk
    }

    private Graph<String, DefaultEdge> localGraph = new DefaultDirectedGraph<String, DefaultEdge>(SupplierUtil.createStringSupplier(), SupplierUtil.createDefaultEdgeSupplier(),false);

    // This method will take a proper file system path down to a .dot file. It will then utilize JGraphT's DOTImporter class to replace the localGraph object with the read .dot file.
    public void parseGraph(String filepath) throws ImportException, NullPointerException{        // Importing graph.
        DOTImporter<String, DefaultEdge> dotImporter = new DOTImporter<>();
        dotImporter.setVertexFactory(label -> label);
        File importFile = new File(filepath);
        dotImporter.importGraph(this.localGraph, importFile);
    }

    // Similar to parseGraph, utilizing JGraphT's API via my .toString method to make a .txt file. The class's graph will be passed as a string to the file writer.
    public void outputGraph(String filepath) throws ExportException, NullPointerException, IOException {
        FileWriter outputFile = new FileWriter(filepath);
        outputFile.write(this.toString());
        outputFile.close();
    }

    // Method for adding a node, or vertex to the local Graph object.
    public boolean addNode(String input) throws NullPointerException{
        // Checking for whitespace, a valid character can be anything else, usually alphanumeric.
        if(hasWhiteSpace(input)){
            return false;
        }

        return this.localGraph.addVertex(input);   // If this evaluates to true, then the add operation was successfully done.
    }

    // Method for removing a node, or vertex from the local Graph object.
    public boolean removeNode(String input) throws IllegalArgumentException, NullPointerException{
        return this.localGraph.removeVertex(input);    // The boolean value determines whether the operation was done correctly or not.
    }

    // The option for adding multiple nodes. It invokes the class's addNode method to save code. Returns an integer for assertion checks.
    public int addNodes(String[] label) throws NullPointerException, ArrayIndexOutOfBoundsException{
        int successCount = 0;

        for (String item : label){
            if(this.addNode(item)){
                successCount++;
            }
        }
        return successCount;
    }

    // Method for removing multiple nodes. It invokes removeNode, defined in this class, to save code. Returns an integer value for assertion checks.
    public int removeNodes(String[] label) throws IllegalArgumentException, NullPointerException, ArrayIndexOutOfBoundsException{
        int successCount = 0;

        for (String item : label) {
            if(this.removeNode(item)){
                successCount++;
            }
        }
        return successCount;
    }

    // Method for adding an edge to the local Graph object. Throws an exception for badly passed input, like a null string.
    public boolean addEdge(String srcLabel, String dstLabel) throws IllegalArgumentException, NullPointerException {
        if(hasWhiteSpace(srcLabel)){ // If the vertex is whitespace, then it shouldn't be added.
            return false;
        }
        if(hasWhiteSpace(dstLabel)){ // The edge cannot be whitespace either.
            return false;
        }

        if(!this.containsNode(srcLabel)){
            this.addNode(srcLabel);
        }
        if(!this.containsNode(dstLabel)){
            this.addNode(dstLabel);
        }

        return this.localGraph.addEdge(srcLabel,dstLabel, new DefaultEdge());
    }

    // Method for removing an edge from the local Graph object. Throws an except for bad input, like null references.
    public boolean removeEdge(String srcLabel, String dstLabel) throws IllegalArgumentException, NullPointerException {
        if (this.localGraph.containsEdge(srcLabel, dstLabel)) {     // Checking if the edge is present or not, to help handle errors.
            this.localGraph.removeEdge(srcLabel, dstLabel);
            return true;
        }
        return false;
    }

    // Method that exports the local graph as a .dot file representation.
    public void outputDOTGraph(String path){
        DOTExporter<String, DefaultEdge> dotExporter = new DOTExporter<>();
        dotExporter.setVertexIdProvider(label -> label);
        File exportFile = new File(path);
        dotExporter.exportGraph(this.localGraph, exportFile);
    }

    // Method that exports the option of either .dot or .png file based on the passed in parameter.
    public void outputGraphics(String path, String format) throws IOException{
        if(format == "PNG"){
            String mediatorPath = "middleman.dot";  // Utilizing relative paths so this can be run on any machine.
            this.outputDOTGraph(mediatorPath);  // Creating a .dot file, so this can be read to bridge the incompatible Graph objects between JGraphT and GraphViz.

            File initFile = new File(mediatorPath); // Creating a new file to read, the newly created "middleman" .dot file.
            InputStream dot = new FileInputStream(initFile);
            MutableGraph g = new Parser().read(dot);    // Reading to transfer the data into the compatible graph type for GraphViz.
            Graphviz.fromGraph(g).width(700).render(Format.PNG).toFile(new File(path)); // Defining file parameters for the output.

            g.graphAttrs()
                    .add(Color.WHITE.gradient(Color.rgb("888888")).background().angle(90))
                    .nodeAttrs().add(Color.WHITE.fill())
                    .nodes().forEach(node ->
                            node.add(
                                    Style.lineWidth(4), Style.FILLED));
            Graphviz.fromGraph(g).width(700).render(Format.PNG).toFile(new File(path));

            initFile.delete();  // Cleaning up the mediator file.
            dot.close();    // Closing the input stream object.
        }else{
            this.outputDOTGraph(path);  // Otherwise, we produce the DOT graph format via another class-side method.
        }

    }

    // This will return a path, the result of the graph algorithm from source to destination.
    public Path GraphSearch(String src, String dst, Algorithm algo){
        Path path = new Path();

        // According to specification, if the vertex doesn't exist, return null;
        if(!(this.localGraph.containsVertex(src))){
            return path = null;
        }

        if(algo.equals(Algorithm.bfs)){     // Part 2  call Algorithm algorithm.traverse.
            TraversalAlgorithm algorithm = new BFSTraversal();
            path = algorithm.algorithmAction(src, dst, this.localGraph);
            return path;

        }else if(algo.equals(Algorithm.dfs)){       // Part 1
            TraversalAlgorithm algorithm = new DFSTraversal();
            path = algorithm.algorithmAction(src, dst, this.localGraph);
            return path;

        }else if(algo.equals(Algorithm.randWalk)){
            TraversalAlgorithm algorithm = new RandomWalkTraversal();
            path = algorithm.algorithmAction(src, dst, this.localGraph);
            return path;
        }else{
            return null;
        }

    }

    // Defining an overridden equals for my complex Graph objects, because the base Object equals doesn't work.
    public boolean equals(GraphManager g){
        if(this.localGraph.vertexSet().equals(g.localGraph.vertexSet())){
            for(DefaultEdge item : g.localGraph.edgeSet()){
                if(!(this.localGraph.edgeSet().toString().contains(item.toString()))){
                    return false;
                }
            }
            for(DefaultEdge item : this.localGraph.edgeSet()){
                if(!(g.localGraph.edgeSet().toString().contains(item.toString()))){
                    return false;
                }
            }
            return true;
        }else{
            return false;
        }
    }

    // Call wrapper for the graph object's defined functions.
    public boolean containsNode(String nSource){
        return this.localGraph.containsVertex(nSource);
    }
    // Another call wrapper, but for the edges instead.
    public boolean containsEdge(String e1, String e2){
        return this.localGraph.containsEdge(e1, e2);
    }

    // Wrapper class to increase readability on invoke directly has a method on objects.
    public boolean hasWhiteSpace(String input){
        if(StringUtils.isWhitespace(input)){
            return true;
        }
        return false;
    }

    public int nodeSize(){
        return this.localGraph.vertexSet().size();
    }

    public int edgeSize(){
        return this.localGraph.edgeSet().size();
    }

    // Formatting method that overrides the toString method for this class, to fit what was expected of me.
    public String toString(){
        String tempSet = "";

        for(DefaultEdge entry : this.localGraph.edgeSet()){
            tempSet += this.localGraph.getEdgeSource(entry) + " -> " + entry.toString().substring((entry.toString().length()/2)+1,entry.toString().length()-1) + ".\n";
        }

        String retVal = "Number of nodes: " + this.localGraph.vertexSet().size() + ".\nLabel of nodes:" + Arrays.toString(this.localGraph.vertexSet().toArray()) +
                "\nNumber of edges: " + this.localGraph.edgeSet().size() + ".\nNodes/Edges Direction(s): \n" + tempSet;
        return retVal;
    }
}
