package student;

import java.util.Comparator;

/**
 * The {@code GameComparator} class provides multiple sorting strategies
 * for {@code BoardGame} objects, following the Strategy Pattern.
 */
public class GameComparator {
    //Prevents instantiation of this utility class
    private GameComparator() {
        throw new UnsupportedOperationException("Utility class - do not instantiate");
    }
    /**
     * Comparator for sorting by name (case-insensitive).
     */
    public static final Comparator<BoardGame> BY_NAME =
            Comparator.comparing(BoardGame::getName, String.CASE_INSENSITIVE_ORDER);

    /**
     * Comparator for sorting by rating.
     */
    public static final Comparator<BoardGame> BY_RATING =
            Comparator.comparingDouble(BoardGame::getRating);

    /**
     * Comparator for sorting by minimum players.
     */
    public static final Comparator<BoardGame> BY_MIN_PLAYERS =
            Comparator.comparingInt(BoardGame::getMinPlayers);

    /**
     * Comparator for sorting by maximum players.
     */
    public static final Comparator<BoardGame> BY_MAX_PLAYERS =
            Comparator.comparingInt(BoardGame::getMaxPlayers);

    /**
     * Comparator for sorting by minimum play time.
     */
    public static final Comparator<BoardGame> BY_MIN_PLAY_TIME =
            Comparator.comparingInt(BoardGame::getMinPlayTime);

    /**
     * Comparator for sorting by maximum play time.
     */
    public static final Comparator<BoardGame> BY_MAX_PLAY_TIME =
            Comparator.comparingInt(BoardGame::getMaxPlayTime);

    /**
     * Comparator for sorting by difficulty.
     */
    public static final Comparator<BoardGame> BY_DIFFICULTY =
            Comparator.comparingDouble(BoardGame::getDifficulty);

    /**
     * Comparator for sorting by rank.
     */
    public static final Comparator<BoardGame> BY_RANK =
            Comparator.comparingInt(BoardGame::getRank);

    /**
     * Comparator for sorting by year published.
     */
    public static final Comparator<BoardGame> BY_YEAR_PUBLISHED =
            Comparator.comparingInt(BoardGame::getYearPublished);
}
