package tracing;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ShortestTraceCalculatorTest {

    @Test
    void calculatesShortestTrace() {
        Node b = new Node("B");
        Node c = new Node("C");
        Node d = new Node("D");
        Node f = new Node("F");
        Edge edgeDB = new Edge(d, b, 1);
        Edge edgeBF = new Edge(b, f, 4);
        Edge edgeFD = new Edge(f, d, 2);
        Edge edgeCF = new Edge(c, f, 3);
        Edge edgeCD = new Edge(c, d, 30);
        d.addEdge(edgeDB);
        b.addEdge(edgeBF);
        f.addEdge(edgeFD);
        c.addEdge(edgeCD);
        c.addEdge(edgeCF);

        Map<String, Node> nodeIdToNodeMap = new HashMap<>();
        nodeIdToNodeMap.put(b.id, b);
        nodeIdToNodeMap.put(c.id, c);
        nodeIdToNodeMap.put(d.id, d);
        nodeIdToNodeMap.put(f.id, f);

        int shortestTrace = ShortestTraceCalculator.calculateShortestTrace("C", "D", nodeIdToNodeMap);

        // C -> F -> D
        assertEquals(5, shortestTrace);
    }

    @Test
    void calculatesShortestTraceWithAnotherGraph() {
        Node a = new Node("A");
        Node b = new Node("B");
        Node c = new Node("C");
        Node d = new Node("D");
        Node e = new Node("E");
        Edge edgeAE = new Edge(a, e, 10);
        Edge edgeEC = new Edge(e, c, 20);
        Edge edgeAD = new Edge(a, d, 1);
        Edge edgeDB = new Edge(d, b, 2);
        Edge edgeBC = new Edge(b, c, 3);

        a.addEdge(edgeAE);
        a.addEdge(edgeAD);
        b.addEdge(edgeBC);
        d.addEdge(edgeDB);
        e.addEdge(edgeEC);

        Map<String, Node> nodeIdToNodeMap = new HashMap<>();
        nodeIdToNodeMap.put(a.id, a);
        nodeIdToNodeMap.put(b.id, b);
        nodeIdToNodeMap.put(c.id, c);
        nodeIdToNodeMap.put(d.id, d);
        nodeIdToNodeMap.put(e.id, e);

        int shortestTrace = ShortestTraceCalculator.calculateShortestTrace("A", "C", nodeIdToNodeMap);

        // A -> D -> B -> C
        assertEquals(6, shortestTrace);
    }

    @Test
    void calculatesShortestTraceWhenCycle() {
        Node b = new Node("B");
        Node c = new Node("C");
        Node d = new Node("D");
        Node f = new Node("F");
        Edge edgeDB = new Edge(d, b, 10);
        Edge edgeBF = new Edge(b, f, 40);
        Edge edgeFD = new Edge(f, d, 20);
        Edge edgeDC = new Edge(d, c, 3);
        Edge edgeCD = new Edge(c, d, 3);
        d.addEdge(edgeDB);
        b.addEdge(edgeBF);
        f.addEdge(edgeFD);
        c.addEdge(edgeCD);
        d.addEdge(edgeDC);

        Map<String, Node> nodeIdToNodeMap = new HashMap<>();
        nodeIdToNodeMap.put(b.id, b);
        nodeIdToNodeMap.put(c.id, c);
        nodeIdToNodeMap.put(d.id, d);
        nodeIdToNodeMap.put(f.id, f);

        // D -> C -> D
        int shortestTrace = ShortestTraceCalculator.calculateShortestTrace("D", "D", nodeIdToNodeMap);
        assertEquals(6, shortestTrace);
    }
}
