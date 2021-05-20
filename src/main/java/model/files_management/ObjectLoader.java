package model.files_management;

import java.util.Optional;

public interface ObjectLoader {
    Optional<Object> load(String path);
    void save(Object object, String path);
}
