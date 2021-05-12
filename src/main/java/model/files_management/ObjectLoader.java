package model.files_management;

// This interface allows to read from file and write to file objects
public interface ObjectLoader {
    Object load(String path);
}
