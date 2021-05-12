package model.files_management;

import java.io.*;
import java.util.Optional;

public class SerialObjectLoader implements ObjectLoader {

    @Override
    public Optional<Object> load(String path) {
        Object object = null;

        try (
                var inputStream = new FileInputStream(path);
                var objectInputStream = new ObjectInputStream(inputStream)
        ) {
            object = objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException ignored) {}

        return Optional.ofNullable(object);
    }

    @Override
    public void save(Object object, String path) {
        try (
                var outputStream = new FileOutputStream(path);
                var objectOutputStream = new ObjectOutputStream(outputStream)
        ) {
            objectOutputStream.writeObject(object);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
