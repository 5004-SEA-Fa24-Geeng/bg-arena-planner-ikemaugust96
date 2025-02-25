package student;

import java.util.*;
import java.util.stream.Collectors;

/**
 * GameListManager manages the internal state of the GameList,
 * ensuring uniqueness and case-insensitive sorting.
 */
public class GameListManager {
    private Set<BoardGame> games;

    /**
     * Constructor initializes the game set with case-insensitive comparison.
     */
    public GameListManager() {
        games = new TreeSet<>(Comparator.comparing(g -> g.getName().toLowerCase()));
    }

    /**
     * Adds a game to the list, ensuring no duplicates (case insensitive).
     *
     * @param game The BoardGame object to add.
     */
    public void addGame(BoardGame game) {
        // TODO: Implement addGame logic
        throw new UnsupportedOperationException("Unimplemented method 'addGame'");
    }

    /**
     * Removes a game by name (case insensitive).
     *
     * @param name The name of the game to remove.
     */
    public void removeGame(String name) {
        // TODO: Implement removeGame logic
        throw new UnsupportedOperationException("Unimplemented method 'removeGame'");
    }

    /**
     * Gets the number of games in the list.
     *
     * @return The count of games.
     */
    public int count() {
        // TODO: Implement count logic
        throw new UnsupportedOperationException("Unimplemented method 'count'");
    }

    /**
     * Retrieves the list of game names sorted case insensitively.
     *
     * @return A sorted list of game names.
     */
    public List<String> getSortedGameNames() {
        // TODO: Implement getSortedGameNames logic
        throw new UnsupportedOperationException("Unimplemented method 'getSortedGameNames'");
    }
}
