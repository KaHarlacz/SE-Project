package files_management.export;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class TXTExporter implements Exporter<String> {
    private final String path;

    public TXTExporter(String fileName) {
        this.path = fileName;
    }

    @Override
    public void export(String text) {
        try (
                var writer = new FileWriter(path);
                var bufferedWriter = new BufferedWriter(writer);
        ) {
            bufferedWriter.write(text);
        } catch (IOException e) {
            throw new IllegalArgumentException("Cannot export to specified path file");
        }
    }
}
