package tracing;

import java.util.ArrayDeque;
import java.util.Map;
import java.util.Queue;

import static java.util.AbstractMap.SimpleEntry;

public final class TraceCountCalculator {
    private TraceCountCalculator() {
        // utility class
    }

    public static int calculateTraceCount(final String start, final String end, final int minHops, final int maxHops, final Map<String, Node> nodes) {
        int traceCount = 0;
        Queue<SimpleEntry<Node, Integer>> queueWithHops = new ArrayDeque<>();
        queueWithHops.add(new SimpleEntry<>(nodes.get(start), 0));

        while (!queueWithHops.isEmpty()) {
            SimpleEntry<Node, Integer> entry = queueWithHops.remove();
            Node currentNode = entry.getKey();
            int currentHop = entry.getValue() + 1;
            if (currentHop <= maxHops) {
                for (Edge edge : currentNode.getAdjacentEdges()) {
                    if (currentHop >= minHops && nodes.get(end).equals(edge.dest)) {
                        traceCount++;
                    }
                    queueWithHops.add(new SimpleEntry<>(edge.dest, currentHop));
                }
            }

        }


        return traceCount;
    }
}
