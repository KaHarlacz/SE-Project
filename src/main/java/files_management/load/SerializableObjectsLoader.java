package files_management.load;

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
        } catch (IOException e) {
            throw new IllegalArgumentException(e.getMessage());
        } catch (ClassNotFoundException e) {
            throw new IllegalArgumentException("Class not found");
        }
    }
}
