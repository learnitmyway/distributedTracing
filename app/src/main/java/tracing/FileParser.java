package tracing;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public final class FileParser {
    private FileParser() {
        // utility class
    }

    public static String parse(final String filePath) throws IOException {
        try (
                FileReader fileReader = new FileReader(filePath);
                BufferedReader bufReader = new BufferedReader(fileReader)
        ) {
            return bufReader.readLine();
        }
    }
}
