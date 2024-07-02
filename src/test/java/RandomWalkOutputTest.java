import org.example.GraphManager;
import org.example.Path;
import org.junit.Before;
import org.junit.Test;

public class RandomWalkOutputTest {
    GraphManager graph;
    GraphManager.Algorithm algo;
    @Before
    public void setup() throws Exception {
        graph = new GraphManager();
        algo = GraphManager.Algorithm.randWalk;
    }
    @Test
    public void randomOutputTest(){
        String[] nodes = {"1","2","3","4","5","6","7","8","9","10","11","12","13","14"};
        Path path = new Path();
        graph.addNodes(nodes);
        graph.addEdge("1","2");
        graph.addEdge("1","3");
        graph.addEdge("1","14");
        graph.addEdge("1","11");
        graph.addEdge("1","12");
        graph.addEdge("2","4");
        graph.addEdge("2","5");
        graph.addEdge("2","11");
        graph.addEdge("4","8");
        graph.addEdge("5","8");
        graph.addEdge("5","13");
        graph.addEdge("6","13");
        graph.addEdge("3","6");
        graph.addEdge("3","7");
        graph.addEdge("3","12");
        graph.addEdge("6","9");
        graph.addEdge("7","9");
        graph.addEdge("11","8");
        graph.addEdge("12","9");
        graph.addEdge("8","10");
        graph.addEdge("9","10");
        graph.addEdge("14","13");
        graph.addEdge("13","10");

        System.out.println(graph.toString());

        for(int i = 0; i < 15; i++){
            System.out.println(String.format("Execution #%s:", i+1));
            path = graph.GraphSearch("1","10", algo);
            System.out.println(path.toString());
        }
    }
    @Test
    public void randomTestFromDotInput2File(){
        Path path = new Path();
        String filepath = "input2.dot";

        graph.parseGraph(filepath);   // Rewrites graphSource with the .dot file's contents.
        System.out.println(graph.toString());

        for(int i = 0; i < 15; i++){
            System.out.println(String.format("Execution #%s:", i+1));
            path = graph.GraphSearch("d","h", algo);
            System.out.println(path.toString());
        }
    }
}
