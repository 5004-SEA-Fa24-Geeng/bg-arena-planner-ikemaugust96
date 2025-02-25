package student;

import java.util.Comparator;
import java.util.stream.Stream;

/**
 * GameSorter provides sorting functionality for streams of BoardGame objects.
 */
public class GameSorter {

    /**
     * Sorts a stream of BoardGame objects by a specified column.
     *
     * @param games The stream of BoardGame objects to sort.
     * @param sortOn The column to sort on (e.g., NAME, RATING).
     * @param ascending Whether to sort in ascending or descending order.
     * @return A sorted stream of BoardGame objects.
     */
    public static Stream<BoardGame> sort(Stream<BoardGame> games, GameData sortOn, boolean ascending) {
        // TODO: Implement sorting logic
        throw new UnsupportedOperationException("Unimplemented method 'sort'");
    }

    /**
     * Retrieves the appropriate Comparator based on the GameData column.
     *
     * @param sortOn The column to sort on.
     * @return The Comparator for sorting BoardGame objects.
     */
    private static Comparator<BoardGame> getComparator(GameData sortOn) {
        // TODO: Implement Comparator retrieval
        throw new UnsupportedOperationException("Unimplemented method 'getComparator'");
    }
}
