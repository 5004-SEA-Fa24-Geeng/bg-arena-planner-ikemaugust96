package student;

import java.util.function.Predicate;

/**
 * The {@code BoardGameFilter} class provides different filtering strategies
 * for {@code BoardGame} objects based on various conditions.
 */
public final class BoardGameFilter {
    //prevents instantiation of this utility class
    private BoardGameFilter() {
        throw new UnsupportedOperationException("Utility class - do not instantiate");
    }
    /**
     * Creates a filter predicate for board games based on their name.
     *
     * @param name The name to compare against.
     * @param op The comparison operation (EQUALS, CONTAINS, etc.).
     * @return A predicate that filters board games based on the given operation.
     */
    public static Predicate<BoardGame> byName(String name, Operations op) {
        return switch (op) {
            case EQUALS -> game -> game.getName().equalsIgnoreCase(name);
            case CONTAINS -> game -> game.getName().toLowerCase().contains(name.toLowerCase());
            case NOT_EQUALS -> game -> !game.getName().equalsIgnoreCase(name);
            case GREATER_THAN -> game -> game.getName().compareToIgnoreCase(name) > 0;
            case GREATER_THAN_EQUALS -> game -> game.getName().compareToIgnoreCase(name) >= 0; // ✅ FIXED!
            case LESS_THAN -> game -> game.getName().compareToIgnoreCase(name) < 0;
            case LESS_THAN_EQUALS -> game -> game.getName().compareToIgnoreCase(name) <= 0; // ✅ FIXED!
            default -> throw new IllegalArgumentException("Unsupported operation for name: " + op);
        };
    }


    /**
     * Creates a filter predicate for board games based on the minimum number of players.
     *
     * @param value The minimum number of players to compare against.
     * @param op The comparison operation (EQUALS, GREATER_THAN, etc.).
     * @return A predicate that filters board games based on minimum players.
     */
    public static Predicate<BoardGame> byMinPlayers(int value, Operations op) {
        return switch (op) {
            case EQUALS -> game -> game.getMinPlayers() == value;
            case GREATER_THAN -> game -> game.getMinPlayers() > value;
            case GREATER_THAN_EQUALS -> game -> game.getMinPlayers() >= value;
            case LESS_THAN -> game -> game.getMinPlayers() < value;
            case LESS_THAN_EQUALS -> game -> game.getMinPlayers() <= value;
            case NOT_EQUALS -> game -> game.getMinPlayers() != value;
            default -> throw new IllegalArgumentException("Unsupported operation for minPlayers: " + op);
        };
    }

    /**
     * Creates a filter predicate for board games based on the maximum number of players.
     *
     * @param value The maximum number of players to compare against.
     * @param op The comparison operation (EQUALS, GREATER_THAN, etc.).
     * @return A predicate that filters board games based on maximum players.
     */
    public static Predicate<BoardGame> byMaxPlayers(int value, Operations op) {
        return switch (op) {
            case EQUALS -> game -> game.getMaxPlayers() == value;
            case GREATER_THAN -> game -> game.getMaxPlayers() > value;
            case GREATER_THAN_EQUALS -> game -> game.getMaxPlayers() >= value;
            case LESS_THAN -> game -> game.getMaxPlayers() < value;
            case LESS_THAN_EQUALS -> game -> game.getMaxPlayers() <= value;
            case NOT_EQUALS -> game -> game.getMaxPlayers() != value;
            default -> throw new IllegalArgumentException("Unsupported operation for maxPlayers: " + op);
        };
    }

    /**
     * Creates a filter predicate for board games based on play time.
     *
     * @param value The maximum play time to compare against.
     * @param op The comparison operation (EQUALS, GREATER_THAN, etc.).
     * @return A predicate that filters board games based on maximum play time.
     */
    public static Predicate<BoardGame> byMaxPlayTime(int value, Operations op) {
        return switch (op) {
            case EQUALS -> game -> game.getMaxPlayTime() == value;
            case GREATER_THAN -> game -> game.getMaxPlayTime() > value;
            case GREATER_THAN_EQUALS -> game -> game.getMaxPlayTime() >= value;
            case LESS_THAN -> game -> game.getMaxPlayTime() < value;
            case LESS_THAN_EQUALS -> game -> game.getMaxPlayTime() <= value;
            case NOT_EQUALS -> game -> game.getMaxPlayTime() != value;
            default -> throw new IllegalArgumentException("Unsupported operation for maxPlayTime: " + op);
        };
    }

    /**
     * Creates a filter predicate for board games based on the minimum play time.
     *
     * @param value The minimum play time to compare against.
     * @param op The comparison operation (EQUALS, GREATER_THAN, etc.).
     * @return A predicate that filters board games based on minimum play time.
     */
    public static Predicate<BoardGame> byMinPlayTime(int value, Operations op) {
        return switch (op) {
            case EQUALS -> game -> game.getMinPlayTime() == value;
            case GREATER_THAN -> game -> game.getMinPlayTime() > value;
            case GREATER_THAN_EQUALS -> game -> game.getMinPlayTime() >= value;
            case LESS_THAN -> game -> game.getMinPlayTime() < value;
            case LESS_THAN_EQUALS -> game -> game.getMinPlayTime() <= value;
            case NOT_EQUALS -> game -> game.getMinPlayTime() != value;
            default -> throw new IllegalArgumentException("Unsupported operation for minPlayTime: " + op);
        };
    }

    /**
     * Creates a filter predicate for board games based on difficulty.
     *
     * @param value The difficulty level to compare against.
     * @param op The comparison operation (EQUALS, GREATER_THAN, etc.).
     * @return A predicate that filters board games based on difficulty.
     */
    public static Predicate<BoardGame> byDifficulty(double value, Operations op) {
        return switch (op) {
            case EQUALS -> game -> game.getDifficulty() == value;
            case GREATER_THAN -> game -> game.getDifficulty() > value;
            case GREATER_THAN_EQUALS -> game -> game.getDifficulty() >= value;
            case LESS_THAN -> game -> game.getDifficulty() < value;
            case LESS_THAN_EQUALS -> game -> game.getDifficulty() <= value;
            case NOT_EQUALS -> game -> game.getDifficulty() != value;
            default -> throw new IllegalArgumentException("Unsupported operation for difficulty: " + op);
        };
    }

    /**
     * Creates a filter predicate for board games based on their ranking.
     *
     * @param value The ranking value to compare against.
     * @param op The comparison operation (EQUALS, GREATER_THAN, etc.).
     * @return A predicate that filters board games based on rank.
     */
    public static Predicate<BoardGame> byRank(int value, Operations op) {
        return switch (op) {
            case EQUALS -> game -> game.getRank() == value;
            case GREATER_THAN -> game -> game.getRank() > value;
            case GREATER_THAN_EQUALS -> game -> game.getRank() >= value;
            case LESS_THAN -> game -> game.getRank() < value;
            case LESS_THAN_EQUALS -> game -> game.getRank() <= value;
            case NOT_EQUALS -> game -> game.getRank() != value;
            default -> throw new IllegalArgumentException("Unsupported operation for rank: " + op);
        };
    }

    /**
     * Creates a filter predicate for board games based on average rating.
     *
     * @param value The average rating to compare against.
     * @param op The comparison operation (EQUALS, GREATER_THAN, etc.).
     * @return A predicate that filters board games based on rating.
     */
    public static Predicate<BoardGame> byRating(double value, Operations op) {
        return switch (op) {
            case EQUALS -> game -> game.getRating() == value;
            case GREATER_THAN -> game -> game.getRating() > value;
            case GREATER_THAN_EQUALS -> game -> game.getRating() >= value;
            case LESS_THAN -> game -> game.getRating() < value;
            case LESS_THAN_EQUALS -> game -> game.getRating() <= value;
            case NOT_EQUALS -> game -> game.getRating() != value;
            default -> throw new IllegalArgumentException("Unsupported operation for averageRating: " + op);
        };
    }

    /**
     * Creates a filter predicate for board games based on the year published.
     *
     * @param value The year to compare against.
     * @param op The comparison operation (EQUALS, GREATER_THAN, etc.).
     * @return A predicate that filters board games based on the year published.
     */
    public static Predicate<BoardGame> byYear(int value, Operations op) {
        return switch (op) {
            case EQUALS -> game -> game.getYearPublished() == value;
            case GREATER_THAN -> game -> game.getYearPublished() > value;
            case GREATER_THAN_EQUALS -> game -> game.getYearPublished() >= value;
            case LESS_THAN -> game -> game.getYearPublished() < value;
            case LESS_THAN_EQUALS -> game -> game.getYearPublished() <= value;
            case NOT_EQUALS -> game -> game.getYearPublished() != value;
            default -> throw new IllegalArgumentException("Unsupported operation for yearPublished: " + op);
        };
    }
}
