package files_management;

import model.data.CookBook;
import files_management.load.Loader;
import files_management.load.SerializableObjectsLoader;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SerialObjectLoaderTest {
    private static final String TEST_PATH = "tmp.obj";
    private static CookBook cookBook;
    private Loader<CookBook> loader = new SerializableObjectsLoader<>(TEST_PATH);

    @BeforeAll
    public static void setUp() {
        cookBook = new CookBook(Set.of());
        try (
                var outputStream = new FileOutputStream(TEST_PATH);
                var output = new ObjectOutputStream(outputStream);
        ) {
            output.writeObject(cookBook);
        } catch (IOException e) {
            throw new IllegalArgumentException("Cannot write to " + TEST_PATH);
        }
    }

    @AfterAll
    public static void cleanUp() {
        new File(TEST_PATH).delete();
    }

    @Test
    public void testLoading() {
        var loaded = loader.load();
        assertTrue(loaded.isPresent());
        assertEquals(loaded.get(), SerialObjectLoaderTest.cookBook);
    }
}