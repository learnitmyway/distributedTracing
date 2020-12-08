package tracing;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public final class App {

    public static final String ANSWER_FORMAT = "%d. %s";

    private App() {
        // utility class
    }

    public static void main(final String[] args) throws IOException {
        for (String line : App.run()) {
            System.out.println(line);
        }
    }

    public static List<String> run() throws IOException {
        String input = FileParser.parse("src/main/resources/input.txt");

        Map<String, Node> nodes = GraphGenerator.generateGraphFrom(input);

        List<String> outputLines = new ArrayList<>();
        outputLines.add(getAverageLatencyAnswer(1, Arrays.asList("A", "B", "C"), nodes));
        outputLines.add(getAverageLatencyAnswer(2, Arrays.asList("A", "D"), nodes));
        outputLines.add(getAverageLatencyAnswer(3, Arrays.asList("A", "D", "C"), nodes));
        outputLines.add(getAverageLatencyAnswer(4, Arrays.asList("A", "E", "B", "C", "D"), nodes));
        outputLines.add(getAverageLatencyAnswer(5, Arrays.asList("A", "E", "D"), nodes));
        outputLines.add(getTraceCountWithHopsAnswer(6, nodes, "C", "C", 1, 3));
        outputLines.add(getTraceCountWithHopsAnswer(7, nodes, "A", "C", 4, 4));
        outputLines.add("8. " + ShortestTraceCalculator.calculateShortestTrace("A", "C", nodes));
        outputLines.add("9. " + ShortestTraceCalculator.calculateShortestTrace("B", "B", nodes));
        outputLines.add(getTraceCountWithLatencyAnswer(10, nodes, "C", "C", 29));

        return outputLines;
    }

    private static String getAverageLatencyAnswer(
            final int questionNumber, final List<String> path, final Map<String, Node> nodes) {
        int averageLatency = AverageLatencyCalculator.calculateLatency(path, nodes);
        return String.format(ANSWER_FORMAT,
                questionNumber,
                averageLatency > -1 ? averageLatency : "NO SUCH TRACE"
        );
    }

    private static String getTraceCountWithHopsAnswer(
            final int questionsNumber, final Map<String, Node> nodes,
            final String start, final String end, final int minHops, final int maxHops
    ) {
        Node startNode = nodes.get(start);
        Node endNode = nodes.get(end);
        int traceCount = TraceCountCalculator.calculateTraceCountWithHops(startNode, endNode, minHops, maxHops);
        return String.format(ANSWER_FORMAT, questionsNumber, traceCount);
    }

    private static String getTraceCountWithLatencyAnswer(
            final int questionsNumber, final Map<String, Node> nodes,
            final String start, final String end, final int maxLatency
    ) {
        Node startNode = nodes.get(start);
        Node endNode = nodes.get(end);
        int traceCount = TraceCountCalculator.calculateTraceCountWithMaxLatency(startNode, endNode, maxLatency);
        return String.format(ANSWER_FORMAT, questionsNumber, traceCount);
    }
}
