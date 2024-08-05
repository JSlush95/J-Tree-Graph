# GraphManager
GraphManager is a Java class designed to facilitate the creation, manipulation, and visualization of directed graphs. It supports importing/exporting graphs in DOT format, adding/removing nodes and edges, and executing various graph traversal algorithms including Breadth-First Search (BFS), Depth-First Search (DFS), and Random Walk.

# Features
- Graph Operations: Add/remove nodes and edges.
- Import/Export: Import graphs from DOT files and export graphs to DOT or PNG formats.
- Traversal Algorithms: Perform BFS, DFS, and Random Walk traversals to find paths between nodes.
- Visualization: Generate visual representations of graphs in PNG format.

### CI/CD Integration via JUnit Tests
- There are a moderate amount of tests done that will execute to test the program's functionalities. Consult these for bonus guidance on inputs and outputs.
- Some of these tests will leave behind PNG or DOT files for verification, etc.

# Usage
## GraphManager Class
### Creating a GraphManager instance:
``GraphManager graphManager = new GraphManager();``<br>
### Importing a Graph from a DOT File:
``graphManager.parseGraph("path/to/graph.dot");``<br>
### Exporting a Graph to a DOT File:
``graphManager.outputDOTGraph("path/to/output.dot");``<br>
### Exporting a Graph to a PNG File:
``graphManager.outputGraphics("path/to/output.png", "PNG");``<br>
## Node and Edge Operations
### Adding Nodes:
``graphManager.addNode("A");``<br>
``graphManager.addNodes(new String[]{"B", "C"});``<br>
### Removing Nodes:
``graphManager.removeNode("A");``<br>
``graphManager.removeNodes(new String[]{"B", "C"});``<br>
### Adding Edges:
``graphManager.addEdge("A", "B");``<br>
### Removing Edges:
``graphManager.removeEdge("A", "B");``<br>
## Graph Traversal
### Performing BFS Traversal:
``Path bfsPath = graphManager.GraphSearch("A", "D", GraphManager.Algorithm.bfs);``<br>
``System.out.println(bfsPath);``<br>
### Performing DFS Traversal:
``Path dfsPath = graphManager.GraphSearch("A", "D", GraphManager.Algorithm.dfs);``<br>
``System.out.println(dfsPath);``<br>
### Performing Random Walk Traversal:
``Path randomWalkPath = graphManager.GraphSearch("A", "D", GraphManager.Algorithm.randWalk);``<br>
``System.out.println(randomWalkPath);``<br>
# Example Output
## DFS Traversal Example:
### Create the Graph Object and Add Nodes and Edges:
``GraphManager graph = new GraphManager();``<br>
``GraphManager.Algorithm algo;``<br>
``String[] nodes = {"7", "4", "9", "3", "2", "5", "8", "1", "6"};``<br>
``Path path = new Path();``<br>
``Path comparePath = new Path();``<br>
``comparePath.add("3 > 2 > 1");``<br>
``graph.addNodes(nodes);``<br>
``graph.addEdge("7", "4");``<br>
``graph.addEdge("7", "9");``<br>
``graph.addEdge("9", "3");``<br>
``graph.addEdge("3", "2");``<br>
``graph.addEdge("3", "5");``<br>
``graph.addEdge("2", "1");``<br>
``graph.addEdge("2", "8");``<br>
``graph.addEdge("8", "6");``<br>
### Test Code for Using DFS Traversal:
``algo = GraphManager.Algorithm.dfs; // Set the algorithm to DFS``<br>
``path = graph.GraphSearch("3", "1", algo); // The returned path is of the format: “3 > 2 > 1”``<br>
``System.out.println("DFS Path: " + path);``<br><br>
### Verify Output:
``assert path.toString().equals(comparePath.toString()); // Test case assertion style with equals``<br>
``System.out.println(graph.toString()); // Print out the path to visually verify the output``<br><br>
## BFS Traversal Example:
### Create the Graph Object and Add Nodes and Edges:
``GraphManager graph = new GraphManager();``<br>
``GraphManager.Algorithm algo;``<br>
``String[] nodes = {"A", "B", "C", "D", "E", "F"};``<br>
``graph.addNodes(nodes);``<br>
``graph.addEdge("A", "B");``<br>
``graph.addEdge("A", "C");``<br>
``graph.addEdge("B", "D");``<br>
``graph.addEdge("C", "E");``<br>
``graph.addEdge("D", "F");``<br>
``graph.addEdge("E", "F");``<br>
### Test Code for Using BFS Traversal:
``algo = GraphManager.Algorithm.bfs;  // Set the algorithm to BFS``<br>
``Path bfsPath = graph.GraphSearch("A", "F", algo);  // The returned path could be: "A > B > D > F"``<br>
``System.out.println("BFS Path: " + bfsPath);``<br><br>
### Verify Output:
* The expected path might be "A > B > D > F" or "A > C > E > F" depending on the implementation.<br>
``System.out.println("Expected BFS Path: A > B > D > F or A > C > E > F");``<br>
``System.out.println("Actual BFS Path: " + bfsPath);``<br><br>
## Random Walk Traversal Example:
### Create the Graph Object and Add Nodes and Edges:
``GraphManager graph = new GraphManager();``<br>
``GraphManager.Algorithm algo;``<br>
``String[] nodes = {"1", "2", "3", "4", "5"};``<br>
``graph.addNodes(nodes);``<br>
``graph.addEdge("1", "2");``<br>
``graph.addEdge("1", "3");``<br>
``graph.addEdge("2", "4");``<br>
``graph.addEdge("3", "4");``<br>
``graph.addEdge("4", "5");``<br>
### Test Code for Using Random Walk:
``algo = GraphManager.Algorithm.randWalk; // Set the algorithm to Random Walk``<br>
``Path randomWalkPath = graph.GraphSearch("1", "5", algo); // The returned path could vary due to randomness``<br>
``System.out.println("Random Walk Path: " + randomWalkPath);``<br><br>
### Verify Output:
* The expected path could vary, since it is random (limited to each node's connected edges), such as "7 -> 4" or "7 -> 9 -> 3 -> 2 -> 1"<br>
``System.out.println("Random Walk Path could vary due to the algorithm's nature.");``<br>
``System.out.println("Actual Random Walk Path: " + randomWalkPath);``<br><br>

# Example Graph Visualization:
      7
     / \
    4   9
         \
          3
         / \
        2   5
       / \
      1   8
           \
            6

