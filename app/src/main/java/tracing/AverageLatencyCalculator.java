package tracing;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public final class AverageLatencyCalculator {
    private AverageLatencyCalculator() {
        // utility class
    }

    public static int calculateLatency(final List<String> path, final Set<Node> nodes) {
        int averageLatency = 0;
        for (int i = 0; i < path.size() - 1; i++) {
            String srcId = path.get(i);
            String destId = path.get(i + 1);
            Optional<Node> sourceNode = findSourceNode(nodes, srcId);
            if (sourceNode.isPresent()) {
                Optional<Edge> edgeWithMatchingDest =
                        findEdgeWithMatchingDest(destId, sourceNode.get());
                if (edgeWithMatchingDest.isPresent()) {
                    averageLatency += edgeWithMatchingDest.get().weight;
                }
            }
        }
        return averageLatency;
    }

    private static Optional<Node> findSourceNode(final Set<Node> nodes, final String srcId) {
        return nodes.stream().filter(node -> srcId.equals(node.id)).findFirst();
    }

    private static Optional<Edge> findEdgeWithMatchingDest(final String destId, final Node srcNode) {
        return srcNode.getAdjacentEdges()
                .stream()
                .filter(edge -> destId.equals(edge.dest.id))
                .findFirst();
    }
}
