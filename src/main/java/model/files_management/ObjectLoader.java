package model.files_management;

import java.util.Optional;

// This interface allows to read from file and write to file objects
public interface ObjectLoader {
    Optional<Object> load(String path);
    void save(Object object, String path);
}
