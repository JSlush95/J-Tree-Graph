import org.example.GraphManager;
import org.example.Path;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class GraphBFSTest {
    GraphManager graph;
    GraphManager.Algorithm algo;
    @Before
    public void setup() throws Exception {
        graph = new GraphManager();
        algo = GraphManager.Algorithm.bfs;
    }
    @Test
    public void testBFSSearchArbitraryNonRoot(){    // Testing with a BFS search on an arbitrary graph.
        String[] nodes = {"7","4","9","3","2","5","8","1","6"};
        Path path = new Path();
        Path comparePath = new Path();
        comparePath.add("3 > 2 > 1");   // CHANGE TO ARROW FORMAT.
        graph.addNodes(nodes);
        graph.addEdge("7","4");
        graph.addEdge("7","9");
        graph.addEdge("9","3");
        graph.addEdge("3","2");
        graph.addEdge("3","5");
        graph.addEdge("2","1");
        graph.addEdge("2","8");
        graph.addEdge("8","6");

        path = graph.GraphSearch("3","1", algo);  // Return a path from vertices 3 to 1.
        Assert.assertTrue(path.toString().equals(comparePath.toString()));
    }

    @Test
    public void testBFSSearchRoot(){    // Testing with a BFS search on an arbitrary graph.
        String[] nodes = {"7","4","9","3","2","5","8","1","6"};
        Path path = new Path();
        Path comparePath = new Path();
        comparePath.add("7 > 9 > 3 > 2 > 1");
        graph.addNodes(nodes);
        graph.addEdge("7","4");
        graph.addEdge("7","9");
        graph.addEdge("9","3");
        graph.addEdge("3","2");
        graph.addEdge("3","5");
        graph.addEdge("2","1");
        graph.addEdge("2","8");
        graph.addEdge("8","6");

        path = graph.GraphSearch("7","1", algo);  // Return a path from vertices 3 to 1.
        Assert.assertTrue(path.toString().equals(comparePath.toString()));
    }

    @Test
    public void testBFSSearchNodeNotFound(){        // Test for a node that doesn't exist in graph.
        String[] nodes = {"7","4","9","3","2","5","10","1","11"};
        Path path = new Path();
        graph.addNodes(nodes);
        graph.addEdge("7","4");
        graph.addEdge("7","9");
        graph.addEdge("9","3");
        graph.addEdge("3","2");
        graph.addEdge("3","5");
        graph.addEdge("2","1");
        graph.addEdge("2","10");
        graph.addEdge("10","11");

        path = graph.GraphSearch("a","1",algo);
        Assert.assertNull(path);    // Specification states the result will be null.
    }
}
