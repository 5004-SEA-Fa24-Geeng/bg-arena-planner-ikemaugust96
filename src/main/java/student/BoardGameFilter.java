package student;

import java.util.function.Predicate;

/**
 * The {@code BoardGameFilter} class provides different filtering strategies
 * for {@code BoardGame} objects based on various conditions.
 */
public class BoardGameFilter {

    /**
     * Filters games by name using different operations.
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
     * Filters games by minimum players using different operations.
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
     * Filters games by maximum players using different operations.
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
     * Filters games by maximum play time using different operations.
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
     * Filters games by minimum play time using different operations.
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
     * Filters games by difficulty using different operations.
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
     * Filters games by rank using different operations.
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
     * Filters games by average rating using different operations.
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
     * Filters games by year published using different operations.
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
