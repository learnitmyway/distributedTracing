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

        Map<String, Node> nodeIdToNodeMap = new HashMap<>();
        nodeIdToNodeMap.put(b.id, b);
        nodeIdToNodeMap.put(d.id, d);
        nodeIdToNodeMap.put(f.id, f);
        int averageLatency = AverageLatencyCalculator.calculateLatency(Arrays.asList("D", "B", "F"), nodeIdToNodeMap);

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

        Map<String, Node> nodeIdToNodeMap = new HashMap<>();
        nodeIdToNodeMap.put(b.id, b);
        nodeIdToNodeMap.put(d.id, d);
        nodeIdToNodeMap.put(f.id, f);

        int averageLatency = AverageLatencyCalculator.calculateLatency(Arrays.asList("F", "B", "D"), nodeIdToNodeMap);

        assertEquals(-1, averageLatency);
    }
}

