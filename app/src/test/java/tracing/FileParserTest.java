package tracing;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FileParserTest {
    @Test
    void parsesFile() throws IOException {
        final String inputString =
                FileParser.parse("src/test/resources/testInput1.txt");

        assertEquals("Graph: DB3, FD9, BF2, BE1, EC7, CE7, CG5, EG6", inputString);
    }
}
