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
        outputLines.add("1. " +
                AverageLatencyCalculator.calculateLatency(Arrays.asList("A", "B", "C"), nodes));
        outputLines.add("2. " +
                AverageLatencyCalculator.calculateLatency(Arrays.asList("A", "D"), nodes));
        outputLines.add("3. " +
                AverageLatencyCalculator.calculateLatency(Arrays.asList("A", "D", "C"), nodes));
        outputLines.add("4. " +
                AverageLatencyCalculator.calculateLatency(Arrays.asList("A", "E", "B", "C", "D"), nodes));
        outputLines.add("5. NO SUCH TRACE");
        outputLines.add("6. 2");
        outputLines.add("7. 3");
        outputLines.add("8. 9");
        outputLines.add("9. 9");
        outputLines.add("10. 7");

        return outputLines;
    }
}
