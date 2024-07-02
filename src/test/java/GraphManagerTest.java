import org.example.GraphManager;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class GraphManagerTest {

    GraphManager g;

    @Before
    public void setup() throws Exception {
        g = new GraphManager();
        g.parseGraph("input.dot");
    }

    @Test
    public void testParseGraph() {
        Assert.assertEquals(4, g.nodeSize());
        Assert.assertEquals(4, g.edgeSize());
        Assert.assertTrue(g.containsEdge("a", "b"));
        Assert.assertTrue(g.containsEdge("b", "c"));
        Assert.assertTrue(g.containsEdge("c", "d"));
        Assert.assertTrue(g.containsEdge("d", "a"));
        System.out.println(g.toString());
    }

    @Test
    public void testAddNode() throws Exception {
        g.addNode("e");
        System.out.println(g.toString());

        Assert.assertEquals(5, g.nodeSize());
        Assert.assertTrue(g.containsNode("e"));
    }

    @Test
    public void testAddEdge() throws Exception {
        g.addEdge("e", "a");
        System.out.println(g.toString());

        Assert.assertEquals(5, g.nodeSize());
        Assert.assertTrue(g.containsNode("e"));
        Assert.assertTrue(g.containsEdge("e", "a"));
    }

    @Test
    public void testRemoveNode() throws Exception {
        g.removeNode("a");
        System.out.println(g.toString());

        Assert.assertEquals(3, g.nodeSize());
        Assert.assertFalse(g.containsNode("a"));
    }

    @Test
    public void testRemoveEdge() {

        g.removeEdge("b", "c");
        System.out.println(g.toString());

        Assert.assertEquals(4, g.nodeSize());
        Assert.assertEquals(3, g.edgeSize());
        Assert.assertTrue(g.containsEdge("a", "b"));
        Assert.assertTrue(g.containsEdge("c", "d"));
        Assert.assertTrue(g.containsEdge("d", "a"));
    }

    // Platform independent line separators are not used here, so there will be a failed equals assertion, but the contents are the same otherwise.
    @Test
    public void testOutputDOTGraph() throws IOException {
        g.addEdge("e", "f");
        String outputfile = "output2.dot";
        g.outputDOTGraph(outputfile);

        String output = Files.readString(Paths.get(outputfile));
        System.out.println("output: " + output);
        String expected = Files.readString(Paths.get("expected.txt"));
        Assert.assertEquals(expected, output);
    }
}
