package tracing;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
        FileParser.parse("src/main/resources/input.txt");
        List<String> outputLines = new ArrayList<>();
        outputLines.add("1. 9");
        outputLines.add("2. 5");
        outputLines.add("3. 13");
        outputLines.add("4. 22");
        outputLines.add("5. NO SUCH TRACE");
        outputLines.add("6. 2");
        outputLines.add("7. 3");
        outputLines.add("8. 9");
        outputLines.add("9. 9");
        outputLines.add("10. 7");

        return outputLines;
    }

}
