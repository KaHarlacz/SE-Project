package model.files_management.export;

import model.data.ShoppingList;

public interface Exporter<T> {
    void export(T t);
}
