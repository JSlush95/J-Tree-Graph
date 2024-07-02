import org.example.GraphManager;
import org.junit.Assert;
import org.junit.Test;


public class GraphAddTest {
    // Simple test for testing the size member for vertices.
    @Test
    public void testAmountVertices(){
        GraphManager graph = new GraphManager();
        graph.addNode("a");
        graph.addNode("2");
        graph.addNode("c");
        graph.addNode("4");
        graph.addNode("e");
        graph.addNode("8");
        graph.addNode("g");
        graph.addNode("16");
        graph.addNode("i");
        Assert.assertEquals(9,graph.nodeSize());
        System.out.println("Vertex Amount Branch:");
        System.out.println(graph.toString());
    }
    // Simple test for the size member of vertices with a null object;
    @Test
    public void testZeroVertices(){
        GraphManager graph = new GraphManager();
        Assert.assertEquals(0,graph.nodeSize());
        System.out.println("0 Vertex Amount Branch:");
        System.out.println(graph.toString());
    }
    // Testing the size member of edges handles an empty graph or not.
    @Test
    public void testZeroEdge(){
        GraphManager graph = new GraphManager();
        Assert.assertEquals(0,graph.edgeSize());
        System.out.println("0 Edge Amount Branch:");
        System.out.println(graph.toString());
    }

    // Standard test where no errors are expected to happen, simple adding of nodes and edges.
    @Test
    public void testAmountEdgesStandard(){
        GraphManager graph = new GraphManager();
        graph.addNode("a1");
        graph.addNode("2b");
        graph.addEdge("a1", "2b");
        graph.addEdge("2b", "a1");
        Assert.assertEquals(2,graph.edgeSize());
        System.out.println("Edge Amount Branch:");
        System.out.println(graph.toString());
    }

    // Adding non-valid edges should cause an ILlegalArgumentException, so they don't exist.
    @Test
    public void testAddEdgeWithNewNodes(){
        GraphManager graph = new GraphManager();

        Assert.assertTrue(graph.addNode("a1"));
        Assert.assertTrue(graph.addNode("2b"));
        Assert.assertTrue(graph.addEdge("a", "1"));
        Assert.assertEquals(4,graph.nodeSize());
    }

    // Testing alphabet format with adding vertices and edges.
    @Test
    public void testAddAlphabetFormatStandard1(){
        GraphManager graph = new GraphManager();
        Assert.assertEquals(true,graph.addNode("a"));
        Assert.assertEquals(true,graph.addNode("b"));
        Assert.assertEquals(true,graph.addNode("c"));

        Assert.assertEquals(true,graph.addEdge("a", "b"));
        Assert.assertEquals(true,graph.addEdge("b", "c"));
        System.out.println("Alphabet Format Branch:");
        System.out.println(graph.toString());
    }

    // Testing if numeric numbers are added properly.
    @Test
    public void testAddNumberFormatStandard(){
        GraphManager graph = new GraphManager();
        Assert.assertEquals(true,graph.addNode("1"));
        Assert.assertEquals(true,graph.addNode("2"));
        Assert.assertEquals(true,graph.addNode("3"));

        Assert.assertEquals(true,graph.addEdge("1", "2"));
        Assert.assertEquals(true,graph.addEdge("2", "1"));
        System.out.println("Number Format Branch:");
        System.out.println(graph.toString());
    }

    // Unit test for special character format. Whitespace characters are not allowed.
    @Test
    public void testSpecialCharFormat(){
        GraphManager graph = new GraphManager();
        Assert.assertEquals(true,graph.addNode("@"));
        Assert.assertEquals(true,graph.addNode("<"));
        Assert.assertEquals(true,graph.addNode("["));
        Assert.assertEquals(false,graph.addNode("["));
        Assert.assertEquals(false,graph.addNode(" "));
        Assert.assertEquals(false,graph.addNode(""));
        Assert.assertEquals(false,graph.addNode(""));

        Assert.assertEquals(true,graph.addEdge("@", "<"));
        Assert.assertEquals(false,graph.addEdge(" ", ""));
        Assert.assertEquals(false,graph.addEdge("", "["));
        Assert.assertEquals(false,graph.addEdge(" ", "@"));
        Assert.assertEquals(true,graph.addEdge("[", "@"));
        System.out.println("Special Char Branch: ");
        System.out.println(graph.toString());
    }
    // Test case for when an element of the array is null, it will cause a NullPointerException.
    @Test(expected = NullPointerException.class)
    public void testAddArrayNodeNullException(){
        GraphManager graph = new GraphManager();
        String[] t1 = {"1", "2", "3", "4", null};

        Assert.assertEquals(-1,graph.addNodes(t1));
    }
    // Standard unit case for adding a String array of numbers into the graph.
    @Test
    public void testAddArrayNonNull(){
        GraphManager graph = new GraphManager();
        String[] t2 = {"1", "2", "3", "4", "5"};

        Assert.assertEquals(t2.length,graph.addNodes(t2));
        graph.toString();
    }
}
