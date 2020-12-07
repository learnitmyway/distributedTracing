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

        Map<String, Node> nodes = new HashMap<>();
        nodes.put(b.id, b);
        nodes.put(c.id, c);
        nodes.put(d.id, d);
        nodes.put(f.id, f);

        int shortestTrace = ShortestTraceCalculator.calculateShortestTrace("C", "D", nodes);

        assertEquals(5, shortestTrace);
    }
}