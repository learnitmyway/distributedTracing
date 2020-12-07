package tracing;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AppTest {
    @Test
    void returnsOutput() {
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

        assertEquals(outputLines, App.run());
    }
}
