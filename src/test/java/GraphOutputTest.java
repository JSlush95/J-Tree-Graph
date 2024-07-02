import org.example.GraphManager;
import org.jgrapht.nio.ImportException;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class GraphOutputTest {
    // Testing the import functionality by presenting a .dot file (in the root directory of this project).
    @Test
    public void graphImportTest(){
        GraphManager graphClone = new GraphManager();   //  Creating a clone that will be used for comparisons.
        GraphManager graphSource = new GraphManager();  // The source graph which will be used for importation.

        graphClone.addNode("a");     // These add nodes/edges are to construct/copy the .dot file to compare.
        graphClone.addNode("b");
        graphClone.addNode("c");
        graphClone.addNode("d");
        graphClone.addEdge("a","b");
        graphClone.addEdge("b","c");
        graphClone.addEdge("c","d");
        graphClone.addEdge("d","a");

        String path = "input.dot";

        graphSource.parseGraph(path);   // Rewrites graphSource with the .dot file's contents.
        boolean tmp = graphSource.equals(graphClone);   // Comparing: a = b;
        Assert.assertTrue(tmp); // If this is true, then graphSource (imported data), and graphClone (manually identically added data) are the same.
    }
    // Testing the equals() member by comparing it with itself, which is the purest test I can do.
    @Test
    public void graphEqualsTest(){
        GraphManager graph = new GraphManager();

        graph.addNode("a");
        graph.addNode("b");
        graph.addEdge("a","b");

        Assert.assertTrue(graph.equals(graph));
    }
    // Using parseGraph on a null object, which makes a NullPointException.
    @Test(expected = NullPointerException.class)
    public void graphImportTestNull(){
        GraphManager graph = new GraphManager();
        String path = null;

        graph.parseGraph(path);
        graph.toString();
    }
    // Test case for expected behavior of an invalid file extension, this throws an exception.
    @Test(expected = ImportException.class)
    public void graphImportTestInvalidFileFormat(){     // Importing an invalid (not .dot) file format will throw an error.
        GraphManager graph = new GraphManager();
        String path = "input2.txt";

        graph.parseGraph(path);
        System.out.println("Invalid File Format Parse:");
        System.out.println(graph.toString());
    }
    // Test case for a .dot text format file, but not .dot extension.
    @Test
    public void graphImportTestTxtFile(){     // Importing a file with its content in .dot specification will work.
        GraphManager graph = new GraphManager();
        String path = "input.txt";

        graph.parseGraph(path);
        System.out.println("Txt File Parse:");
        System.out.println(graph.toString());
    }

    // Testing a path with empty string, which throws an ImportException.
    @Test(expected = ImportException.class)
    public void graphImportTestEmptyPath(){
        GraphManager graph = new GraphManager();
        String path = "";

        graph.parseGraph(path);
        graph.toString();
    }
    // Standard outputting of a graph, which takes the .toString() from the class and writes it to a .txt file.
    @Test
    public void outputGraphStandardTest() throws IOException {
        GraphManager graph = new GraphManager();
        String path = "outputGraphStandardTest.txt";

        graph.addNode("a");
        graph.addNode("b");
        graph.addEdge("a","b");

        graph.outputGraph(path);
    }
    // Testing whether the data is corrupted in some manner (purity test). Done via two separate objects and comparisons.
    @Test
    public void graphExportPurityTestDOT(){
        GraphManager graph = new GraphManager();
        GraphManager graphClone = new GraphManager();
        String path = "graphPurityTest.dot";
        File file = new File(path);

        graph.addNode("a");
        graph.addNode("b");
        graph.addEdge("a","b");     // Manually constructing the graph that is to be imported.

        graph.outputDOTGraph(path); // Outputs the graph into a .dot file.
        graphClone.parseGraph(path);    // Using graphClone to copy the recently created .dot file.
        Assert.assertTrue(graph.equals(graphClone));    // Asserting whether they are equal or not, should it pass, then they are the same (import success).
        file.delete();      // Clear the file after execution for safety reasons.
    }
    // Passing a null string will create a NullPointerException.
    @Test(expected = NullPointerException.class)
    public void graphExportTestNull() throws IOException{
        GraphManager graph = new GraphManager();
        String path = null;

        graph.outputGraph(path);
        graph.toString();
    }
    // Empty string path will result in an IOException.
    @Test(expected = IOException.class)
    public void graphExportTestNonExistentPath() throws IOException{
        GraphManager graph = new GraphManager();
        String path = "";

        graph.outputGraph(path);
        graph.toString();
    }
    // Assertion test for the exported test by checking whether the file exists, then deletes it.
    @Test
    public void graphExportPNGStandardTest() throws IOException{
        GraphManager graph = new GraphManager();
        String path = "exportPNGStandardTest.png";
        File file = new File(path);

        graph.addNode("a");
        graph.addNode("b");
        graph.addEdge("a","b");

        graph.outputGraphics(path, "PNG");
        Assert.assertTrue(file.exists());
        file.delete();
    }

    @Test       // Test that exports the graph into a png, but leaves it behind.
    public void graphExportPNGLeaveBehindPNGArtifactNumberFormat() throws IOException{ //
        GraphManager graph = new GraphManager();
        String path = "exportPNGStandardTest2.png";
        File file = new File(path);

        graph.addNode("1");
        graph.addNode("2");
        graph.addNode("a");
        graph.addEdge("2","1");
        graph.addEdge("a","2");
        graph.addEdge("a","1");

        graph.outputGraphics(path, "PNG");
        Assert.assertTrue(file.exists());
    }
    // Passing a path that is null will result in a Null Pointer Exception.
    @Test(expected = NullPointerException.class)
    public void graphExportPNGTestNonExistentPath() throws IOException{
        GraphManager graph = new GraphManager();
        String path = null;

        graph.outputGraphics(path, "PNG");
        graph.toString();
    }
    // Exporting a graph into .dot file format.
    @Test
    public void graphExportDOTStandardTest() throws IOException{
        GraphManager graph = new GraphManager();
        String path = "exportDOTStandardTest.dot";
        File file = new File(path);

        graph.addNode("a");
        graph.addNode("b");
        graph.addEdge("a","b");

        graph.outputGraphics(path, "DOT");
        Assert.assertTrue(file.exists());
    }
}
