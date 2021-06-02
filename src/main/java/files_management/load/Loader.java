package files_management.load;

import java.util.Optional;

public interface Loader<T> {
    Optional<T> load();
}
