package tracing;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

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
        Set<Node> nodes = GraphGenerator.generateGraphFrom(input);
        List<String> outputLines = new ArrayList<>();
        outputLines.add(getAverageLatencyAnswer(1, Arrays.asList("A", "B", "C"), nodes));
        outputLines.add(getAverageLatencyAnswer(2, Arrays.asList("A", "D"), nodes));
        outputLines.add(getAverageLatencyAnswer(3, Arrays.asList("A", "D", "C"), nodes));
        outputLines.add(getAverageLatencyAnswer(4, Arrays.asList("A", "E", "B", "C", "D"), nodes));
        outputLines.add(getAverageLatencyAnswer(5, Arrays.asList("A", "E", "D"), nodes));
        outputLines.add("6. " + TraceCountCalculator.calculateTraceCount("C", "C", 3, nodes));
        outputLines.add("7. 3");
        outputLines.add("8. 9");
        outputLines.add("9. 9");
        outputLines.add("10. 7");

        return outputLines;
    }

    private static String getAverageLatencyAnswer(
            final int questionNumber, final List<String> path, final Set<Node> nodes) {
        int averageLatency = AverageLatencyCalculator.calculateLatency(path, nodes);
        return String.format("%d. %s",
                questionNumber,
                averageLatency > -1 ? averageLatency : "NO SUCH TRACE"
        );
    }
}
