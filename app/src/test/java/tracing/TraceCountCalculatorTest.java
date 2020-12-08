package tracing;

import org.junit.jupiter.api.Test;

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

        int traceCount = TraceCountCalculator.calculateTraceCountWithHops(d, d, 1, 2);

        // DC -> CD
        assertEquals(1, traceCount);
    }

    @Test
    void calculatesNumberOfTracesWithExactHops() {
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

        int traceCount = TraceCountCalculator.calculateTraceCountWithHops(d, d, 3, 3);

        // DB -> BF -> FD
        assertEquals(1, traceCount);
    }

    @Test
    void calculatesTraceCountWithMaxLatency() {
        Node b = new Node("B");
        Node c = new Node("C");
        Node d = new Node("D");
        Node f = new Node("F");

        Edge edgeDB = new Edge(d, b, 1);
        Edge edgeBD = new Edge(b, d, 1);
        Edge edgeBF = new Edge(b, f, 4);
        Edge edgeFD = new Edge(f, d, 1);
        Edge edgeDC = new Edge(d, c, 2);
        Edge edgeCD = new Edge(c, d, 2);

        b.addEdge(edgeBF);
        b.addEdge(edgeBD);
        f.addEdge(edgeFD);
        c.addEdge(edgeCD);
        d.addEdge(edgeDC);
        d.addEdge(edgeDB);

        int traceCount = TraceCountCalculator.calculateTraceCountWithMaxLatency(d, d, 6);

        // 1. D -> B -> F -> D
        // 2. D -> B -> D
        // 3. D -> C -> D
        // 4. D -> B -> D -> C -> D
        // 5. D -> C -> D -> B -> D
        // 6. D -> B -> D -> B -> D
        // 7. D -> B -> D -> B -> D -> B -> D
        assertEquals(7, traceCount);
    }
}
