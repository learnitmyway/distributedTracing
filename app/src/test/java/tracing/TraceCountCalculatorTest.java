package tracing;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TraceCountCalculatorTest {
    @Test
    void calculatesNumberOfTracesWithMaxHops() {
        Node b = new Node("B");
        Node c = new Node("C");
        Node d = new Node("D");
        Node f = new Node("F");
        Edge edgeDB = new Edge(d, b, 1);
        Edge edgeBF = new Edge(b, f, 4);
        Edge edgeFD = new Edge(f, d, 2);
        Edge edgeDC = new Edge(d, c, 3);
        Edge edgeCD = new Edge(c, d, 3);
        d.addEdge(edgeDB);
        b.addEdge(edgeBF);
        f.addEdge(edgeFD);
        c.addEdge(edgeCD);
        d.addEdge(edgeDC);

        Set<Node> nodes = new HashSet<>(Arrays.asList(f, d, b));
        int traceCount = TraceCountCalculator.calculateTraceCount("D", "D", 2, nodes);

        // DC -> CD
        assertEquals(1, traceCount);


    }
}
