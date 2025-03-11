package student;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * The {@code GameList} class implements the {@code IGameList} interface
 * and is responsible for storing board games while delegating sorting
 * to {@code GameSorter} with dynamic comparators.
 */
public class GameList implements IGameList {
    private final List<BoardGame> storedGames;

    /**
     * Constructs an empty GameList.
     */
    public GameList() {
        this.storedGames = new ArrayList<>();
    }

    @Override
    public void addToList(String str, Stream<BoardGame> filtered) {
        storedGames.clear();
        storedGames.addAll(filtered.toList());
    }

    @Override
    public void removeFromList(String str) throws IllegalArgumentException {
        storedGames.removeIf(game -> game.getName().equalsIgnoreCase(str));
    }

    @Override
    public List<String> getGameNames() {
        return storedGames.stream()
                .map(BoardGame::getName)
                .sorted(String.CASE_INSENSITIVE_ORDER) // ✅ Ensures case-insensitive sorting
                .toList();
    }

    @Override
    public int count() {
        return storedGames.size();
    }

    @Override
    public void clear() {
        storedGames.clear();
    }

    @Override
    public void saveGame(String filename) {
        throw new UnsupportedOperationException("Save functionality not implemented yet.");
    }
}
