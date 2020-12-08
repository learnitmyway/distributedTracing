package tracing;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GraphGeneratorTest {
    @Test
    void generatesGraphFromInput() {
        String input = "Graph: DB3, FD9, BF2, BE1, EC7, CE7, CG5, EG6";
        Map<String, Node> nodeIdToNodeMap = GraphGenerator.generateGraphFrom(input);

        assertEquals(6, nodeIdToNodeMap.size());

        Node e = nodeIdToNodeMap.get("E");
        assertEquals(2, e.getAdjacentEdges().size());

        Edge edge1 = e.getAdjacentEdges().get(0);
        Edge edge2 = e.getAdjacentEdges().get(1);

        // FIXME: surely there a simpler way to test this?
        assertEquals("E", edge1.src.id);
        assertEquals("C", edge1.dest.id);
        assertEquals(7, edge1.weight);
        assertEquals("E", edge2.src.id);
        assertEquals("G", edge2.dest.id);
        assertEquals(6, edge2.weight);
    }
}

