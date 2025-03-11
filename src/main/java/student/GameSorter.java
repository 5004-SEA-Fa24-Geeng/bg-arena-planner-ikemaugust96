package student;

import java.util.Comparator;
import java.util.stream.Stream;


/**
 * The {@code GameSorter} class provides sorting functionality for board games.
 */
public final class GameSorter {
    //Prevents instantiation of this utility class
    private GameSorter() {
        throw new UnsupportedOperationException("Utility class - do not instantiate");
    }
    /**
     * Sorts a stream of {@code BoardGame} objects based on a given attribute.
     *
     * @param games     The stream of board games.
     * @param sortOn    The sorting attribute (e.g., NAME, RATING).
     * @param ascending Whether to sort in ascending order.
     * @return A sorted stream of board games.
     */
    public static Stream<BoardGame> sort(Stream<BoardGame> games, GameData sortOn, boolean ascending) {
        if (sortOn == null) {
            throw new IllegalArgumentException("Sorting attribute cannot be null.");
        }

        Comparator<BoardGame> comparator = switch (sortOn) {
            case NAME -> GameComparator.BY_NAME;
            case RATING -> GameComparator.BY_RATING;
            case MIN_PLAYERS -> GameComparator.BY_MIN_PLAYERS;
            case MAX_PLAYERS -> GameComparator.BY_MAX_PLAYERS;
            case MIN_TIME -> GameComparator.BY_MIN_PLAY_TIME;
            case MAX_TIME -> GameComparator.BY_MAX_PLAY_TIME;
            case DIFFICULTY -> GameComparator.BY_DIFFICULTY;
            case RANK -> GameComparator.BY_RANK;
            case YEAR -> GameComparator.BY_YEAR_PUBLISHED;
            default -> throw new IllegalArgumentException("Unsupported sorting type: " + sortOn);
        };

        return games.sorted(ascending ? comparator : comparator.reversed());
    }
}
