package student;

import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * The {@code Planner} class implements the {@code IPlanner} interface
 * and provides filtering and sorting capabilities for board games.
 */
public class Planner implements IPlanner {
    private final Set<BoardGame> games; // Stores the games

    /**
     * Constructs a Planner with a given set of board games.
     *
     * @param games The set of games to filter and sort.
     */
    public Planner(Set<BoardGame> games) {
        this.games = games;
    }

    /**
     * Filters the games based on a given filter string.
     *
     * @param filter The filtering condition.
     * @return A stream of filtered board games.
     */
    @Override
    public Stream<BoardGame> filter(String filter) {
        Predicate<BoardGame> predicate = FilterParser.parse(filter)
                .stream()
                .reduce(x -> true, Predicate::and);

        return games.stream().filter(predicate);
    }

    /**
     * Filters and sorts the games based on a given attribute.
     *
     * @param filter The filtering condition.
     * @param sortOn The sorting attribute (e.g., NAME, RATING).
     * @return A sorted stream of filtered board games.
     */
    @Override
    public Stream<BoardGame> filter(String filter, GameData sortOn) {
        return GameSorter.sort(filter(filter), sortOn, true); // Uses GameSorter.sort()
    }

    /**
     * Filters and sorts the games based on a given attribute and order.
     *
     * @param filter The filtering condition.
     * @param sortOn The sorting attribute (e.g., NAME, RATING).
     * @param ascending Whether to sort in ascending order.
     * @return A sorted stream of filtered board games.
     */
    @Override
    public Stream<BoardGame> filter(String filter, GameData sortOn, boolean ascending) {
        return GameSorter.sort(filter(filter), sortOn, ascending); // Uses GameSorter.sort()
    }

    /**
     * Resets the planner (if needed).
     */
    @Override
    public void reset() {
        // Reset logic (if needed, e.g., clearing cached filters)
    }
}


