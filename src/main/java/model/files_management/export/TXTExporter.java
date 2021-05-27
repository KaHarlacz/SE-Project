package model.files_management.export;

import model.files_management.Paths;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class TXTExporter implements Exporter<String> {
    private final String fileName;

    public TXTExporter(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void export(String text) {
        try (
                var writer = new FileWriter(Paths.EXPORT_PATH);
                var bufferedWriter = new BufferedWriter(writer);
        ) {
            bufferedWriter.write(text);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
