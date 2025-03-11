import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import student.BoardGame;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import student.Planner;
import student.IPlanner;
import student.GameData;


/**
 * JUnit test for the Planner class.
 * Just a sample test to get you started, also using
 * setup to help out. 
 */
public class TestPlanner {
    static Set<BoardGame> games;

    @BeforeAll
    public static void setup() {
        games = new HashSet<>();
        games.add(new BoardGame("17 days", 6, 1, 8, 70, 70, 9.0, 600, 9.0, 2005));
        games.add(new BoardGame("Chess", 7, 2, 2, 10, 20, 10.0, 700, 10.0, 2006));
        games.add(new BoardGame("Go", 1, 2, 5, 30, 30, 8.0, 100, 7.5, 2000));
        games.add(new BoardGame("Go Fish", 2, 2, 10, 20, 120, 3.0, 200, 6.5, 2001));
        games.add(new BoardGame("golang", 4, 2, 7, 50, 55, 7.0, 400, 9.5, 2003));
        games.add(new BoardGame("GoRami", 3, 6, 6, 40, 42, 5.0, 300, 8.5, 2002));
        games.add(new BoardGame("Monopoly", 8, 6, 10, 20, 1000, 1.0, 800, 5.0, 2007));
        games.add(new BoardGame("Tucano", 5, 10, 20, 60, 90, 6.0, 500, 8.0, 2004));
    }


    /**
     * Test filtering by name.
     */
    @Test
    public void testFilterByName() {
        IPlanner planner = new Planner(games); // Creating new instance inside test
        List<BoardGame> filtered = planner.filter("name == Go").toList();
        assertEquals(1, filtered.size());
        assertEquals("Go", filtered.get(0).getName());
    }

    /**
     * Test filtering by year published.
     */
    @Test
    public void testFilterByYearPublished() {
        IPlanner planner = new Planner(games); // Following YOUR pattern
        List<BoardGame> filtered = planner.filter("yearPublished > 2003").toList();
        assertEquals(4, filtered.size()); // 17 days, Chess, Monopoly, Tucano
    }

    /**
     * Test filtering by difficulty.
     */
    @Test
    public void testFilterByDifficulty() {
        IPlanner planner = new Planner(games);
        List<BoardGame> filtered = planner.filter("difficulty >= 8.0").toList();
        assertEquals(3, filtered.size()); // Chess, Go, 17 days
    }

    /**
     * Test filtering with multiple conditions.
     */
    @Test
    public void testFilterByMultipleConditions() {
        IPlanner planner = new Planner(games);
        List<BoardGame> filtered = planner.filter("difficulty >= 7.0, yearPublished < 2005").toList();
        assertEquals(2, filtered.size()); // Go, golang, GoRami
    }

    /**
     * Test sorting by rating (ascending).
     */
    @Test
    public void testSortByRatingAscending() {
        IPlanner planner = new Planner(games);
        List<BoardGame> sorted = planner.filter("", GameData.RATING, true).toList();
        assertEquals("Monopoly", sorted.get(0).getName()); // Lowest rating
        assertEquals("Chess", sorted.get(sorted.size() - 1).getName()); // Highest rating
    }

    /**
     * Test sorting by rating (descending).
     */
    @Test
    public void testSortByRatingDescending() {
        IPlanner planner = new Planner(games);
        List<BoardGame> sorted = planner.filter("", GameData.RATING, false).toList();
        assertEquals("Chess", sorted.get(0).getName()); // Highest rating
        assertEquals("Monopoly", sorted.get(sorted.size() - 1).getName()); // Lowest rating
    }

    /**
     * Test filtering and sorting together.
     */
    @Test
    public void testFilterAndSortByDifficulty() {
        IPlanner planner = new Planner(games);
        List<BoardGame> sorted = planner.filter("yearPublished >= 2000", GameData.DIFFICULTY, false).toList();
        assertEquals("Chess", sorted.get(0).getName()); // Highest difficulty
        assertEquals("Monopoly", sorted.get(sorted.size() - 1).getName()); // Lowest difficulty
    }

    /**
     * Test filtering by minimum playtime.
     */
    @Test
    public void testFilterByMinPlayTime() {
        IPlanner planner = new Planner(games);
        List<BoardGame> filtered = planner.filter("minPlayTime >= 50").toList();
        assertEquals(3, filtered.size()); // 17 days, golang, Tucano
    }

    /**
     * Test filtering by max players.
     */
    @Test
    public void testFilterByMaxPlayers() {
        IPlanner planner = new Planner(games);
        List<BoardGame> filtered = planner.filter("maxPlayers > 6").toList();
        assertEquals(5, filtered.size()); // Monopoly, Tucano
    }

    /**
     * Test handling of invalid filter conditions.
     */
    @Test
    public void testInvalidFilterThrowsException() {
        IPlanner planner = new Planner(games);
        assertThrows(IllegalArgumentException.class, () -> planner.filter("invalidFilter > 100"));
    }

    /**
     * Test handling of invalid sorting attribute.
     */
    @Test
    public void testInvalidSortingThrowsException() {
        IPlanner planner = new Planner(games);
        assertThrows(IllegalArgumentException.class, () -> planner.filter("", null, true));
    }
}