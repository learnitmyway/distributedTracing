package tracing;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AverageLatencyCalculatorTest {

    //CHECKSTYLE.OFF: MagicNumber
    @Test
    void calculatesAverageLatency() {
        Node b = new Node("B");
        Node d = new Node("D");
        Node f = new Node("F");
        Edge edgeDB = new Edge(d, b, 1);
        Edge edgeBF = new Edge(b, f, 4);
        d.addEdge(edgeDB);
        b.addEdge(edgeBF);

        Map<String, Node> nodes = new HashMap<>();
        nodes.put(b.id, b);
        nodes.put(d.id, d);
        nodes.put(f.id, f);
        int averageLatency = AverageLatencyCalculator.calculateLatency(Arrays.asList("D", "B", "F"), nodes);

        assertEquals(5, averageLatency);
    }
    //CHECKSTYLE.ON: MagicNumber

    @Test
    void noSuchTrace() {
        Node b = new Node("B");
        Node d = new Node("D");
        Node f = new Node("F");
        Edge edgeDB = new Edge(d, b, 1);
        Edge edgeBF = new Edge(b, f, 4);
        d.addEdge(edgeDB);
        b.addEdge(edgeBF);

        Map<String, Node> nodes = new HashMap<>();
        nodes.put(b.id, b);
        nodes.put(d.id, d);
        nodes.put(f.id, f);

        int averageLatency = AverageLatencyCalculator.calculateLatency(Arrays.asList("F", "B", "D"), nodes);

        assertEquals(-1, averageLatency);
    }
}

