package tracing;

import java.util.ArrayDeque;
import java.util.Optional;
import java.util.Queue;
import java.util.Set;

import static java.util.AbstractMap.SimpleEntry;

public final class TraceCountCalculator {
    private TraceCountCalculator() {
        // utility class
    }

    public static int calculateTraceCount(final String start, final String end, final int maxHops, final Set<Node> nodes) {
        int traceCount = 0;
        Optional<Node> startNode = findNode(nodes, start);
        Optional<Node> endNode = findNode(nodes, end);
        if (startNode.isPresent() && endNode.isPresent()) {
            Queue<SimpleEntry<Node, Integer>> queueWithHops = new ArrayDeque<>();
            queueWithHops.add(new SimpleEntry<>(startNode.get(), 0));

            while (!queueWithHops.isEmpty()) {
                SimpleEntry<Node, Integer> entry = queueWithHops.remove();
                Node currentNode = entry.getKey();
                int currentHop = entry.getValue() + 1;
                if (currentHop <= maxHops) {
                    for (Edge edge : currentNode.getAdjacentEdges()) {
                        if (endNode.get().equals(edge.dest)) {
                            traceCount++;
                        }
                        queueWithHops.add(new SimpleEntry<>(edge.dest, currentHop));
                    }
                }
            }

        }


        return traceCount;
    }

    private static Optional<Node> findNode(final Set<Node> nodes, final String id) {
        return nodes.stream().filter(node -> id.equals(node.id)).findFirst();
    }
}
