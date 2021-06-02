package files_management.export;

public interface Exporter<T> {
    void export(T t);
}
