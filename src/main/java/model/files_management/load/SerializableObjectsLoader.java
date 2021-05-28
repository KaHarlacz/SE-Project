package model.files_management.load;

import java.io.*;
import java.util.Optional;

public class SerializableObjectsLoader<T extends Serializable> implements Loader<T> {
    private final String path;

    public SerializableObjectsLoader(String path) {
        this.path = path;
    }

    @Override
    public Optional<T> load() {
        try (
                var inputStream = new FileInputStream(path);
                var objectInputStream = new ObjectInputStream(inputStream)
        ) {
            return Optional.ofNullable((T) objectInputStream.readObject());
        } catch (IOException | ClassNotFoundException e) {
            throw new IllegalArgumentException("Cannot load T object from specified file");
        }
    }
}
