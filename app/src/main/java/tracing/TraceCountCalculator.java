package tracing;

import java.util.ArrayDeque;
import java.util.Queue;

import static java.util.AbstractMap.SimpleEntry;

public final class TraceCountCalculator {
    private TraceCountCalculator() {
        // utility class
    }

    /**
     * A variation of Breadth-First Search where the hop count is used to determine when to stop searching
     */
    public static int calculateTraceCountWithHops(final Node start, final Node end, final int minHops, final int maxHops) {
        int traceCount = 0;
        Queue<SimpleEntry<Node, Integer>> queueWithHops = new ArrayDeque<>();
        queueWithHops.add(new SimpleEntry<>(start, 0));

        while (!queueWithHops.isEmpty()) {
            SimpleEntry<Node, Integer> entry = queueWithHops.remove();
            Node currentNode = entry.getKey();
            int currentHop = entry.getValue() + 1;
            if (currentHop <= maxHops) {
                for (Edge edge : currentNode.getAdjacentEdges()) {
                    if (currentHop >= minHops && end.equals(edge.dest)) {
                        traceCount++;
                    }
                    queueWithHops.add(new SimpleEntry<>(edge.dest, currentHop));
                }
            }

        }


        return traceCount;
    }

    /**
     * A variation of Breadth-First Search where the max latency is used to determine when to stop searching
     */
    public static int calculateTraceCountWithMaxLatency(final Node start, final Node end, final int maxLatency) {
        int traceCount = 0;
        Queue<SimpleEntry<Node, Integer>> queueWithLatency = new ArrayDeque<>();
        queueWithLatency.add(new SimpleEntry<>(start, 0));

        while (!queueWithLatency.isEmpty()) {
            SimpleEntry<Node, Integer> entry = queueWithLatency.remove();
            Node currentNode = entry.getKey();
            for (Edge edge : currentNode.getAdjacentEdges()) {
                int currentLatency = entry.getValue() + edge.weight;
                if (currentLatency <= maxLatency) {
                    if (end.equals(edge.dest)) {
                        traceCount++;
                    }
                    queueWithLatency.add(new SimpleEntry<>(edge.dest, currentLatency));
                }
            }

        }


        return traceCount;
    }
}
