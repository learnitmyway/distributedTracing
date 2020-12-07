package tracing;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public final class App {
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
        outputLines.add("6. " + TraceCountCalculator.calculateTraceCount("C", "C", 1, 3, nodes));
        outputLines.add("7. " + TraceCountCalculator.calculateTraceCount("A", "C", 4, 4, nodes));
        outputLines.add("8. " + ShortestTraceCalculator.calculateShortestTrace("A", "C", nodes));
        outputLines.add("9. 9");
        outputLines.add("10. 7");

        return outputLines;
    }

    private static String getAverageLatencyAnswer(
            final int questionNumber, final List<String> path, final Map<String, Node> nodes) {
        int averageLatency = AverageLatencyCalculator.calculateLatency(path, nodes);
        return String.format("%d. %s",
                questionNumber,
                averageLatency > -1 ? averageLatency : "NO SUCH TRACE"
        );
    }
}
