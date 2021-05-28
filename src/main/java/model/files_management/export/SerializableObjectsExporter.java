package model.files_management.export;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class SerializableObjectsExporter<T extends Serializable> implements Exporter<T> {
    private final String path;

    public SerializableObjectsExporter(String path) {
        this.path = path;
    }

    @Override
    public void export(T t) {
        try (
                var outputStream = new FileOutputStream(path);
                var objectOutputStream = new ObjectOutputStream(outputStream)
        ) {
            objectOutputStream.writeObject(t);
        } catch (IOException e) {
            throw new IllegalArgumentException("Cannot export object to specified path file");
        }
    }
}
