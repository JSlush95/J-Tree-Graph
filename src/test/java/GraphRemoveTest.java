import org.example.GraphManager;
import org.junit.Assert;
import org.junit.Test;

public class GraphRemoveTest {
    // Unit test for removing nodes on a standard graph.
    @Test
    public void removeNodeTestNonEmptyGraph(){
        GraphManager graph = new GraphManager();

        graph.addNode("ab");
        graph.addNode("csd");
        graph.addNode("1");
        graph.addNode("@");
        graph.addEdge("ab", "1");
        graph.addEdge("@", "csd");
        System.out.println("1st Phase (Full Graph):");
        System.out.println(graph.toString());

        Assert.assertEquals(true, graph.removeNode("ab"));
        System.out.println("2nd Phase (remove ab):");
        System.out.println(graph.toString());

        System.out.println("3rd Phase (remove @):");
        Assert.assertEquals(true, graph.removeNode("@"));
        System.out.println(graph.toString());

        Assert.assertEquals(false, graph.removeNode("ab"));
    }
    // Removing Nodes, but specifically if the list wasn't given any information beforehand.
    @Test
    public void removeNodeNullTest(){
        GraphManager graph = new GraphManager();

        Assert.assertEquals(false,graph.removeNode("x"));
    }
    // Removing edges on a standard graph.
    @Test
    public void removeEdgeTestGraphStandard(){
        GraphManager graph = new GraphManager();

        graph.addNode("xx");
        graph.addNode("yy");
        graph.addNode("z1");
        graph.addNode("&&");
        graph.addEdge("xx", "z1");
        System.out.println("1st Phase (Full Graph):");
        System.out.println(graph.toString());

        Assert.assertEquals(true,graph.removeEdge("xx","z1"));
        System.out.println("2nd Phase (remove edge xx and z1):");
        System.out.println(graph.toString());

        Assert.assertEquals(false,graph.removeEdge("xx","z1"));
        Assert.assertEquals(false,graph.removeEdge("x","y"));
    }
    // Unit test for removing edges twice, the first one being true (applied) and the 2nd false (not applied).
    @Test
    public void testRemoveEdge(){
        GraphManager graph = new GraphManager();

        graph.addEdge("xx", "z1");
        System.out.println("1st Phase (Full Graph):");
        System.out.println(graph.toString());

        Assert.assertEquals(1,graph.edgeSize());
        Assert.assertTrue(graph.removeEdge("xx","z1"));
        Assert.assertEquals(0, graph.edgeSize());
        System.out.println("2nd Phase (remove edge xx and z1):");
        System.out.println(graph.toString());

        Assert.assertFalse(graph.removeEdge("xx","z1"));

    }
    // Basic test for remove edges on a graph with no information.
    @Test
    public void removeEdgeNullTest(){
        GraphManager graph = new GraphManager();

        Assert.assertEquals(false,graph.removeEdge("xa","yb"));
    }
    // Basic unit test for removing multiple nodes via a String array.
    @Test
    public void removeNodeArrayTestStandard(){
        GraphManager graph = new GraphManager();
        String[] t = {"&&","yy","z1"};

        graph.addNode("xx");
        graph.addNode("yy");
        graph.addNode("z1");
        graph.addNode("&&");
        graph.addNode("@y");
        graph.addEdge("xx", "z1");

        Assert.assertEquals(t.length,graph.removeNodes(t));
    }
    // Test case for removeNode(array[]) arguments that have a partial null element in them. Does not cause an exception.
    @Test
    public void removeNodeArrayPartialNullTest(){
        GraphManager graph = new GraphManager();
        String[] t = {"1", null};

        Assert.assertEquals(0, graph.removeNodes(t));      // Returns 0 instead of -1.
    }
    // Test case for a null String with no values in it. Doesn't cause an exception.
    @Test
    public void removeNodeArrayFullNullTest(){
        GraphManager graph = new GraphManager();
        String[] t = new String[2];

        Assert.assertEquals(0, graph.removeNodes(t));
    }
}
