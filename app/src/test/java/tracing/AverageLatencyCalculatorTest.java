package tracing;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

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

        Set<Node> nodes = new HashSet<>(Arrays.asList(b, d, f));
        int averageLatency = AverageLatencyCalculator.calculateLatency(Arrays.asList("D", "B", "F"), nodes);

        assertEquals(5, averageLatency);
    }
    //CHECKSTYLE.ON: MagicNumber

}

