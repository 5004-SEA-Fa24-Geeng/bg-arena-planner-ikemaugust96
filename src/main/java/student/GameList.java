package student;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.stream.Stream;

/**
 * The {@code GameList} class implements the {@code IGameList} interface
 * and is responsible for storing board games while delegating sorting
 * to {@code GameSorter} with dynamic comparators.
 *
 * <p>Key Features:</p>
 * <ul>
 *   <li>Maintains a list of unique board games.</li>
 *   <li>Ensures case-insensitive sorting for game names.</li>
 *   <li>Supports adding/removing games using names, indexes, or ranges.</li>
 * </ul>
 */
public class GameList implements IGameList {
    private final List<BoardGame> storedGames;

    /**
     * Constructs an empty {@code GameList}.
     */
    public GameList() {
        this.storedGames = new ArrayList<>();
    }

    /**
     * Adds games to the list based on the specified input string.
     *
     * <p>The method supports the following formats:</p>
     * <ul>
     *   <li>{@code "all"} - Adds all games from the filtered stream.</li>
     *   <li>{@code "1"} - Adds the first game from the filtered list (1-based index).</li>
     *   <li>{@code "1-5"} - Adds games from index 1 to 5 (inclusive), adjusting for list size.</li>
     * </ul>
     *
     * @param str the command specifying which games to add (name, index, range, or "all").
     * @param filtered the filtered stream of {@code BoardGame} objects.
     * @throws IllegalArgumentException if the input string is invalid or out of range.
     */
    @Override
    public void addToList(String str, Stream<BoardGame> filtered) {
        List<BoardGame> filteredList = filtered.toList();

        //  First, check if `str` is a valid game name
        Optional<BoardGame> gameByName = filteredList.stream()
                .filter(game -> game.getName().equalsIgnoreCase(str))
                .findFirst();

        if (gameByName.isPresent()) {
            if (!storedGames.contains(gameByName.get())) {
                storedGames.add(gameByName.get());
            }
            return; // 🚀 Exit early since we processed the name
        }

        // If not a name, process numbers, ranges, or "all"
        if (str.equalsIgnoreCase(ADD_ALL)) {
            for (BoardGame game : filteredList) {
                if (!storedGames.contains(game)) {
                    storedGames.add(game);
                }
            }
        } else if (str.matches("\\d+")) {
            int index = Integer.parseInt(str) - 1;
            if (index < 0 || index >= filteredList.size()) {
                throw new IllegalArgumentException("Index out of range.");
            }
            BoardGame game = filteredList.get(index);
            if (!storedGames.contains(game)) {
                storedGames.add(game);
            }
        } else if (str.matches("\\d+-\\d+")) {
            String[] parts = str.split("-");
            int start = Integer.parseInt(parts[0]) - 1;
            int end = Integer.parseInt(parts[1]) - 1;

            if (start < 0 || start >= filteredList.size() || end < start) {
                throw new IllegalArgumentException("Invalid range.");
            }

            end = Math.min(end, filteredList.size() - 1);
            for (int i = start; i <= end; i++) {
                BoardGame game = filteredList.get(i);
                if (!storedGames.contains(game)) {
                    storedGames.add(game);
                }
            }
        } else {
            throw new IllegalArgumentException("Invalid input format: " + str);
        }
    }


    /**
     * Removes games from the list based on the specified input string.
     *
     * <p>The method supports the following formats:</p>
     * <ul>
     *   <li>{@code "all"} - Removes all games from the list.</li>
     *   <li>{@code "1"} - Removes the first game from the list (1-based index).</li>
     *   <li>{@code "1-5"} - Removes games from index 1 to 5 (inclusive).</li>
     *   <li>{@code "GameName"} - Removes a game by its name (case-insensitive).</li>
     * </ul>
     *
     * @param str the command specifying which games to remove.
     * @throws IllegalArgumentException if the input string is invalid or out of range.
     */
    @Override
    public void removeFromList(String str) throws IllegalArgumentException {
        if (str.equalsIgnoreCase(ADD_ALL)) {
            clear();
            return;
        }

        //  First, check if `str` is a valid game name
        Optional<BoardGame> gameByName = storedGames.stream()
                .filter(game -> game.getName().equalsIgnoreCase(str))
                .findFirst();

        if (gameByName.isPresent()) {
            storedGames.remove(gameByName.get());
            return; // 🚀 Exit early since we processed the name
        }

        //  If not a name, process numbers or ranges
        List<BoardGame> toRemove = new ArrayList<>();

        if (str.matches("\\d+")) {
            int index = Integer.parseInt(str) - 1;
            if (index < 0 || index >= storedGames.size()) {
                throw new IllegalArgumentException("Index out of range.");
            }
            toRemove.add(storedGames.get(index));
        } else if (str.matches("\\d+-\\d+")) {
            String[] parts = str.split("-");
            int start = Integer.parseInt(parts[0]) - 1;
            int end = Integer.parseInt(parts[1]) - 1;

            if (start < 0 || start >= storedGames.size() || end < start) {
                throw new IllegalArgumentException("Invalid range.");
            }

            end = Math.min(end, storedGames.size() - 1);
            toRemove.addAll(storedGames.subList(start, end + 1));
        } else {
            throw new IllegalArgumentException("Game not found: " + str);
        }

        if (toRemove.isEmpty()) {
            throw new IllegalArgumentException("No valid games to remove.");
        }

        storedGames.removeAll(toRemove);
    }


    /**
     * Returns a list of game names in ascending order (case-insensitive).
     *
     * @return a sorted list of game names.
     */
    @Override
    public List<String> getGameNames() {
        return storedGames.stream()
                .map(BoardGame::getName)
                .sorted(String.CASE_INSENSITIVE_ORDER)
                .toList();
    }

    /**
     * Returns the number of games currently stored in the list.
     *
     * @return the number of stored games.
     */
    @Override
    public int count() {
        return storedGames.size();
    }

    /**
     * Removes all games from the list.
     */
    @Override
    public void clear() {
        storedGames.clear();
    }

    /**
     * Saves the list of game names to a file.
     *
     * <p>The output file will contain one game name per line, written in the same order
     * as returned by {@link #getGameNames()}.</p>
     *
     * <p>If the file already exists, it will be overwritten.</p>
     *
     * @param filename the name of the file to save the list to.
     * @throws UnsupportedOperationException as this functionality is not implemented yet.
     */
    @Override
    public void saveGame(String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (String gameName : getGameNames()) {  // Uses sorted list from getGameNames()
                writer.write(gameName);
                writer.newLine(); // Ensures each name is on a new line
            }
        } catch (IOException e) {
            throw new RuntimeException("Error saving game list to file: " + filename, e);
        }
    }
}
