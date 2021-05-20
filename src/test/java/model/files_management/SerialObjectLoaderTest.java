package model.files_management;

import model.data.Dish;
import model.data.ShoppingList;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class SerialObjectLoaderTest {
    private static final String TEST_PATH = "tmp.obj";
    private ObjectLoader loader = new SerialObjectLoader();

    @AfterAll
    public static void cleanUp() {
        var file = new File(TEST_PATH);
        file.delete();
    }

    @Test
    public void loadObjectFromNotExistingFile() {
        var file = new File(TEST_PATH);

        file.delete();

        var loaded = loader.load(TEST_PATH);

        assertFalse(loaded.isPresent());
    }

    @Test
    public void loadObjectFromExistingFile() {
        var mockDish = mock(Dish.class);
        var file = new File(TEST_PATH);

        // To be certain
        file.delete();

        loader.save(mockDish, TEST_PATH);

        assertTrue(file.exists());
    }

    @Test
    public void saveObjectInAlreadyExistingFile() throws IOException {
        var mockShoppingList = mock(ShoppingList.class);
        var file = new File(TEST_PATH);

        file.delete();
        file.createNewFile();
        var initialLength = file.length();

        loader.save(mockShoppingList, TEST_PATH);
        var finalLength = file.length();

        assertTrue(finalLength > initialLength);
    }

    @Test
    public void saveAndReadFromFile() {
        var integers = List.of(1, 2, 4, 7, 2);
        var file = new File(TEST_PATH);

        file.delete();

        loader.save(integers, TEST_PATH);
        var loaded = loader.load(TEST_PATH);

        // Just for this test intentionally skipped isPresent
        assertEquals(loaded.get(), integers);
    }
}